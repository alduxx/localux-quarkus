package br.com.bb.localux;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LightsService {
    public String lightStatus(int lightId){
        return "on";
    }
}
