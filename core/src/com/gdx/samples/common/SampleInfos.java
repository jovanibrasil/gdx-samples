package com.gdx.samples.common;

import com.gdx.samples.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleInfos {

    public static final List<SampleInfo> ALL = Arrays.asList(
            ApplicationListenerSample.SAMPLE_INFO,
            GdxModuleInfoSample.SAMPLE_INFO,
            InputListeningSample.SAMPLE_INFO,
            InputPollingSample.SAMPLE_INFO,
            ReflectionSample.SAMPLE_INFO,
            Main.SAMPLE_INFO,
            OrthographicCameraSample.SAMPLE_INFO,
            ViewPortsSample.SAMPLE_INFO,
            SpriteBatchSample.SAMPLE_INFO,
            ShapeRendererSample.SAMPLE_INFO,
            Box2DSample.SAMPLE_INFO,
            Box2DFruitsExample.SAMPLE_INFO
    );

    /**
     * Returns a list of sample names.
     *
     * @return
     */
    public static List<String> getSampleNames(){
        List<String> ret = new ArrayList<>();

        for (SampleInfo info : ALL) {
            ret.add(info.getName());
        }

        Collections.sort(ret);
        return ret;
    }

    public static SampleInfo find(String name){
        if(name == null || name.isEmpty()){
            throw  new IllegalArgumentException("Name argument os required");
        }

        for (SampleInfo info : ALL) {
            if(info.getName().equals(name)){
                return info;
            }
        }
        throw new IllegalArgumentException("Could not find sample with name="+name);
    }

    private SampleInfos() {}

}
