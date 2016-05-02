package com.universe.introduces.第10章;

import java.util.EnumSet;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner6;

/**
 * 绋嬪簭鍚嶇О瑙勮寖鐨勭紪璇戝櫒鎻掍欢锛�<br>
 * 濡傛灉绋嬪簭鍛藉悕涓嶅悎瑙勮寖锛屽皢浼氳緭鍑轰竴涓紪璇戝櫒鐨刉ARNING淇℃伅
 */
public class NameChecker {
    private final Messager messager;

    NameCheckScanner nameCheckScanner = new NameCheckScanner();

    NameChecker(ProcessingEnvironment processsingEnv) {
        this.messager = processsingEnv.getMessager();
    }

    /**
     * 瀵笿ava绋嬪簭鍛藉悕杩涜妫�鏌ワ紝鏍规嵁銆奐ava璇█瑙勮寖銆嬬涓夌増绗�6.8鑺傜殑瑕佹眰锛孞ava绋嬪簭鍛藉悕搴斿綋绗﹀悎涓嬪垪鏍煎紡锛�
     * 
     * <ul>
     * <li>绫绘垨鎺ュ彛锛氱鍚堥┘寮忓懡鍚嶆硶锛岄瀛楁瘝澶у啓銆�
     * <li>鏂规硶锛氱鍚堥┘寮忓懡鍚嶆硶锛岄瀛楁瘝灏忓啓銆�
     * <li>瀛楁锛�
     * <ul>
     * <li>绫汇�佸疄渚嬪彉閲�: 绗﹀悎椹煎紡鍛藉悕娉曪紝棣栧瓧姣嶅皬鍐欍��
     * <li>甯搁噺: 瑕佹眰鍏ㄩ儴澶у啓銆�
     * </ul>
     * </ul>
     */
    public void checkNames(Element element) {
        nameCheckScanner.scan(element);
    }

    /**
     * 鍚嶇О妫�鏌ュ櫒瀹炵幇绫伙紝缁ф壙浜咼DK 1.6涓柊鎻愪緵鐨凟lementScanner6<br>
     * 灏嗕細浠isitor妯″紡璁块棶鎶借薄璇硶鏍戜腑鐨勫厓绱�
     */
    private class NameCheckScanner extends ElementScanner6<Void, Void> {

        /**
         * 姝ゆ柟娉曠敤浜庢鏌ava绫�
         */
        @Override
        public Void visitType(TypeElement e, Void p) {
            scan(e.getTypeParameters(), p);
            checkCamelCase(e, true);
            super.visitType(e, p);
            return null;
        }

        /**
         * 妫�鏌ユ柟娉曞懡鍚嶆槸鍚﹀悎娉�
         */
        @Override
        public Void visitExecutable(ExecutableElement e, Void p) {
            if (e.getKind() == ElementKind.METHOD) {
                Name name = e.getSimpleName();
                if (name.contentEquals(e.getEnclosingElement().getSimpleName())) messager.printMessage(WARNING, "涓�涓櫘閫氭柟娉� 鈥�" + name + "鈥濅笉搴斿綋涓庣被鍚嶉噸澶嶏紝閬垮厤涓庢瀯閫犲嚱鏁颁骇鐢熸贩娣�", e);
                checkCamelCase(e, false);
            }
            super.visitExecutable(e, p);
            return null;
        }

        /**
         * 妫�鏌ュ彉閲忓懡鍚嶆槸鍚﹀悎娉�
         */
        @Override
        public Void visitVariable(VariableElement e, Void p) {
            // 濡傛灉杩欎釜Variable鏄灇涓炬垨甯搁噺锛屽垯鎸夊ぇ鍐欏懡鍚嶆鏌ワ紝鍚﹀垯鎸夌収椹煎紡鍛藉悕娉曡鍒欐鏌�
            if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e))
                checkAllCaps(e);
            else
                checkCamelCase(e, false);
            return null;
        }

        /**
         * 鍒ゆ柇涓�涓彉閲忔槸鍚︽槸甯搁噺
         */
        private boolean heuristicallyConstant(VariableElement e) {
            if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE)
                return true;
            else if (e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(PUBLIC, STATIC, FINAL)))
                return true;
            else {
                return false;
            }
        }

        /**
         * 妫�鏌ヤ紶鍏ョ殑Element鏄惁绗﹀悎椹煎紡鍛藉悕娉曪紝濡傛灉涓嶇鍚堬紝鍒欒緭鍑鸿鍛婁俊鎭�
         */
        private void checkCamelCase(Element e, boolean initialCaps) {
            String name = e.getSimpleName().toString();
            boolean previousUpper = false;
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);

            if (Character.isUpperCase(firstCodePoint)) {
                previousUpper = true;
                if (!initialCaps) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "鍚嶇О鈥�" + name + "鈥濆簲褰撲互灏忓啓瀛楁瘝寮�澶�", e);
                    return;
                }
            } else if (Character.isLowerCase(firstCodePoint)) {
                if (initialCaps) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "鍚嶇О鈥�" + name + "鈥濆簲褰撲互澶у啓瀛楁瘝寮�澶�", e);
                    return;
                }
            } else
                conventional = false;

            if (conventional) {
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (Character.isUpperCase(cp)) {
                        if (previousUpper) {
                            conventional = false;
                            break;
                        }
                        previousUpper = true;
                    } else
                        previousUpper = false;
                }
            }

            if (!conventional) messager.printMessage(Diagnostic.Kind.WARNING, "鍚嶇О鈥�" + name + "鈥濆簲褰撶鍚堥┘寮忓懡鍚嶆硶锛圕amel Case Names锛�", e);
        }

        /**
         * 澶у啓鍛藉悕妫�鏌ワ紝瑕佹眰绗竴涓瓧姣嶅繀椤绘槸澶у啓鐨勮嫳鏂囧瓧姣嶏紝鍏朵綑閮ㄥ垎鍙互鏄笅鍒掔嚎鎴栧ぇ鍐欏瓧姣�
         */
        private void checkAllCaps(Element e) {
            String name = e.getSimpleName().toString();

            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);

            if (!Character.isUpperCase(firstCodePoint))
                conventional = false;
            else {
                boolean previousUnderscore = false;
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (cp == (int) '_') {
                        if (previousUnderscore) {
                            conventional = false;
                            break;
                        }
                        previousUnderscore = true;
                    } else {
                        previousUnderscore = false;
                        if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
                            conventional = false;
                            break;
                        }
                    }
                }
            }

            if (!conventional) messager.printMessage(Diagnostic.Kind.WARNING, "甯搁噺鈥�" + name + "鈥濆簲褰撳叏閮ㄤ互澶у啓瀛楁瘝鎴栦笅鍒掔嚎鍛藉悕锛屽苟涓斾互瀛楁瘝寮�澶�", e);
        }
    }
}

