package com.ze.spellchecker;

/**
 * Created by ZE-C on 2017/12/8.
 */

public class WordComparor {

    public enum Status {
        Equal, Similar, NotFound
    }

    public Status compareSameLength(String source, String target) {
        if( source.equals(target) ){
            return Status.Equal;
        }
        int diffNum = 0;
        for (int i = 0; i < source.length(); i++) {
            if( source.charAt(i) != target.charAt(i)){
                diffNum += 1;
            }
            if( diffNum > 1  ){
                return Status.NotFound;
            }
        }
        if(diffNum==1){
            return Status.Similar;
        }else {
            return Status.NotFound;
        }
    }

    public Status compare(String source, String target) {
        if (source == null || target == null) {
            return Status.NotFound;
        }
        int len = Math.abs(source.length() - target.length());
        if (len > 1) {
            return Status.NotFound;
        }
        if (len == 1) {
            if (source.contains(target)) {
                return Status.Similar;
            }
            if (target.contains(source)) {
                return Status.Similar;
            }
            return Status.NotFound;
        }
        if (len == 0) {
            return compareSameLength(source, target);
        }
        return Status.NotFound;
    }
}
