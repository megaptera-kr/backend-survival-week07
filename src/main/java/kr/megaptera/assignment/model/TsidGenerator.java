package kr.megaptera.assignment.model;

import com.github.f4b6a3.tsid.TsidCreator;

public class TsidGenerator {
    public static String generateTsid() {
        return TsidCreator.getTsid256().toString();
    }
}
