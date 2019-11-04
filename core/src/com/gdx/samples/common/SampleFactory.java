package com.gdx.samples.common;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class SampleFactory {

    public static SampleBase newSample(String name){
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("Parameter name is required");

        SampleInfo info = SampleInfos.find(name);

        try {
            return (SampleBase) ClassReflection.newInstance(info.getClazz());
        } catch (ReflectionException e) {
            throw new RuntimeException("Can not create samplewith name="+name, e);
        }

    }

    private SampleFactory() {}

}
