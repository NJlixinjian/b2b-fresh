package cn.sigo.sigocloudprovider.config.serverport;

import cn.hutool.core.util.RandomUtil;

/**
 * 随机生成一个端口，并只生成一次
 */
public class RandomServerPort {

    private int serverPort;

    private final int start = 0;
    private final int end = 65535;

    public int nextValue(int start) {
        return nextValue(start, end);
    }

    public int nextValue(int start, int end) {
        start = start < this.start? this.start: start;
        end = end > this.end? this.end: end;

        if (serverPort == 0){
            synchronized (this){
                if (serverPort == 0){
                    serverPort = RandomUtil.randomInt(start, end);
                }
            }
        }
        return serverPort;
    }
}
