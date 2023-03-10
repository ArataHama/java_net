package ex01;

// 各経路情報を持つクラス

public class RouteInfo{
    
    // 宛先ネットワークアドレス
    protected String nwAddress;

    // サブネットマスク
    protected String subNetMask;

    // Next HopのIP
    protected String nextHop;

    // メトリック:ルーティングアルゴリズムで優先度決める値
    // protected double metric;
    
    public RouteInfo(String nwAddress, String subNetMask, String nextHop/*, double metric*/){
        this.nwAddress = nwAddress;
        this.subNetMask = subNetMask;
        this.nextHop = nextHop;
        // this.metric = metric;
    }
    public RouteInfo(){

    }
    

    public String getNwAddress() {
        return this.nwAddress;
    }
    public void setNwAddress(String nwAddress) {
        this.nwAddress = nwAddress;
    }
    public String getSubNetMask(){
        return this.subNetMask;
    }
    public void setSubNetMask(String subNetMask){
        this.subNetMask = subNetMask;
    }
    public String getNextHop() {
        return this.nextHop;
    }
    public void setNextHop(String nextHop) {
        this.nextHop = nextHop;
    }
    // public double getMetric() {
    //     return this.metric;
    // }
    // public void setMetric(double metric) {
    //     this.metric = metric;
    // }
}