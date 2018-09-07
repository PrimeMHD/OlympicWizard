package chizaitongji.example.com.chizaitongji.Utils;

import java.util.List;

import chizaitongji.example.com.chizaitongji.Bean.Nation;

public class NationCodeGenerator {



    private static int BasicCode=0;

    public static int Generate(List<Nation>nations){

        BasicCode++;
        return BasicCode;
    }
}
