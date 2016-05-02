package com.universe.introduces.第10章;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.SourceVersion;

// 鍙互鐢�"*"琛ㄧず鏀寔鎵�鏈堿nnotations
@SupportedAnnotationTypes("*")
// 鍙敮鎸丣DK 1.6鐨凧ava浠ｇ爜
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class NameCheckProcessor extends AbstractProcessor {

    private NameChecker nameChecker;

    /**
     * 鍒濆鍖栧悕绉版鏌ユ彃浠�
     */
    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameChecker(processingEnv);
    }

    /**
     * 瀵硅緭鍏ョ殑璇硶鏍戠殑鍚勪釜鑺傜偣杩涜杩涜鍚嶇О妫�鏌�
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements())
                nameChecker.checkNames(element);
        }
        return false;
    }

}


