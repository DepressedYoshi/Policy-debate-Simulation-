public class Judge {
    private String name;
    private boolean flow;
    //todo implement judge baises

    public Judge(String name) {
        this.name = name;
        this.flow = false;
    }
    public Judge(String name, boolean flow) {
        this.name = name;
        this.flow = flow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlow() {
        return flow;
    }

    public void setFlow(boolean flow) {
        this.flow = flow;
    }
}
