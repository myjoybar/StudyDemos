package com.example;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import static com.example.Config.ROUTER_MANAGER_PKN;

/**
 * Created by joybar on 04/11/2017.
 */
//http://www.jianshu.com/p/9e34defcb76f
    //http://blog.csdn.net/github_35180164/article/details/52121038
    //http://www.jianshu.com/p/95f12f72f69a
@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Map<RouterPatternAndScheme, String> mStaticRouterMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mStaticRouterMap.clear();
        for (TypeElement element : annotations) {
            if (element.getQualifiedName().toString().equals(RegisterRouter.class.getCanonicalName())) {
                processRouterMap1(element, roundEnv);

//                try {
//                    processRouterMap2(element, roundEnv);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }
        }

        return true;
    }

    private void process1(RoundEnvironment roundEnv) {

        Set<? extends Element> routerElements = roundEnv.getElementsAnnotatedWith(RegisterRouter.class);
        for (Element e : routerElements) {
            if (!(e instanceof TypeElement)) {
                continue;
            }
            TypeElement typeElement = (TypeElement) e;
            String patten = typeElement.getAnnotation(RegisterRouter.class).patten();
            String scheme = typeElement.getAnnotation(RegisterRouter.class).scheme();

            System.out.println("patten=" + patten);
            System.out.println("scheme=" + scheme);
        }

    }


    private void processRouterMap1(TypeElement element, RoundEnvironment roundEnv) {
        Set<? extends Element> routerElements = roundEnv.getElementsAnnotatedWith(RegisterRouter.class);
        for (Element e : routerElements) {
            if (!(e instanceof TypeElement)) {
                continue;
            }
            TypeElement typeElement = (TypeElement) e;//代表被注解的元素
            String patten = typeElement.getAnnotation(RegisterRouter.class).patten();
            String scheme = typeElement.getAnnotation(RegisterRouter.class).scheme();
            // Class的完整路径
            String classFullName = typeElement.getQualifiedName().toString();
            System.out.println("patten=" + patten);
            System.out.println("scheme=" + scheme);
            System.out.println("classFullName=" + classFullName);

            if (mStaticRouterMap.get(new RouterPatternAndScheme(patten, scheme)) == null) {
                mStaticRouterMap.put(new RouterPatternAndScheme(patten, scheme), classFullName);
            }
        }
        writeComponentFile();


    }


    private void writeComponentFile() {

        for (Map.Entry<RouterPatternAndScheme, String> entry : mStaticRouterMap.entrySet()) {
            String patten = entry.getKey().pattern;
            String methodStatement = Config.getMethodName(patten);
            String className = entry.getValue().replace(".", "_") + Config.ROUTER_MANAGER_CLASS_NAME;
            JavaFileObject javaFileObject = null;

            try {
                javaFileObject = mFiler.createSourceFile(className);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(javaFileObject.openWriter());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            printWriter.println("package " + Config.ROUTER_MANAGER_PKN + ";");
            printWriter.println("import android.app.Activity;");
            printWriter.println("import android.app.Service;");
            printWriter.println("import android.content.BroadcastReceiver;");
            printWriter.println("public class " + className + " {");
            printWriter.println("public static void addRouter() {");

            printWriter.println("com.joybar.appcommponentlib.router1.routermanager.RouterManager.getInstance()." + methodStatement + "(\"" + ((RouterPatternAndScheme) entry.getKey()).scheme
                    + "\", " + entry.getValue() + ".class);");

            printWriter.println("}");
            printWriter.println("}");
            printWriter.flush();
            printWriter.close();
        }
    }


    private void processRouterMap2(TypeElement element, RoundEnvironment roundEnv) throws IOException {

        Set<? extends Element> routerElements = roundEnv.getElementsAnnotatedWith(RegisterRouter.class);
        for (Element e : routerElements) {
            if (!(e instanceof TypeElement)) {
                continue;
            }
            TypeElement typeElement = (TypeElement) e;
            String patten = typeElement.getAnnotation(RegisterRouter.class).patten();
            String scheme = typeElement.getAnnotation(RegisterRouter.class).scheme();
            String fullName = typeElement.getQualifiedName().toString();

            System.out.println("element.getQualifiedName=" + typeElement.getQualifiedName());
            System.out.println("fullName=" + fullName);
            System.out.println("element.getSimpleName=" + element.getSimpleName());
            System.out.println("patten=" + patten);
            System.out.println("scheme=" + scheme);


            MethodSpec main = MethodSpec.methodBuilder("main")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(void.class)
                    .addParameter(String[].class, "args")
                    .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                    .build();
            MethodSpec computeRange = computeRange("multiply10to20", 10, 20, "*");


            MethodSpec addRouter = computeAddRouter(Config.ROUTER_MANAGER_METHOD_NAME, patten, scheme, typeElement.getQualifiedName().toString());
            TypeSpec routerManger = TypeSpec.classBuilder(fullName.replace(".", "_") + Config.ROUTER_MANAGER_CLASS_NAME)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(main)
                    .addMethod(computeRange)
                    .addMethod(addRouter)
                    .build();
            JavaFile javaFile = JavaFile.builder(ROUTER_MANAGER_PKN, routerManger)
                    .build();
            javaFile.writeTo(mFiler);

        }

    }


    private MethodSpec computeRange(String name, int from, int to, String op) {
        return MethodSpec.methodBuilder(name)
                .returns(int.class)
                .addStatement("int result = 0")
                .beginControlFlow("for (int i = " + from + "; i < " + to + "; i++)")
                .addStatement("result = result " + op + " i")
                .endControlFlow()
                .addStatement("return result")
                .build();
    }

    private MethodSpec computeAddRouter(String methodName, String patten, String scheme, String classFullName) {

        classFullName = classFullName + ".class";

        String methodStatement = Config.getMethodName(patten);
        return MethodSpec.methodBuilder(methodName)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                //.addStatement("com.joybar.appcommponentlib.router1.routermanager.RouterManager.getInstance()."+methodStatement+"(\"" +patten+ scheme +  "\"," + classFullName + ")")
                .addStatement("com.joybar.appcommponentlib.router1.routermanager.RouterManager.getInstance()." + methodStatement + "(\"" + scheme + "\"," + classFullName + ")")
                .build();

    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(RegisterRouter.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    public static class RouterPatternAndScheme {
        String pattern;
        String scheme;

        public RouterPatternAndScheme(String pattern, String scheme) {
            this.pattern = pattern;
            this.scheme = scheme;
        }

        @Override
        public int hashCode() {
            return pattern.hashCode() + scheme.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof RouterPatternAndScheme) { //
                RouterPatternAndScheme patternAndScheme = (RouterPatternAndScheme) obj;
                return (scheme.equals(patternAndScheme.scheme) && pattern == patternAndScheme.pattern);
            }
            return super.equals(obj);
        }
    }

}
