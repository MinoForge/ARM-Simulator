package Simulation;

public class Controller {

    private int DEFAULT_REGISTER_NUM = 13;

    private String[] data;
    private String[] instructions;

    //private  instructor;
    public static int PC = 0;


    public Controller(String[] data, String[] instructions) {
        this.data = data;
        this.instructions = instructions;
        init(this.data);

    }


    /**
     * Initializes the simulator, registers, stack, etc.
     */
    public void init(String[] data) {
        long[] registers = new long[DEFAULT_REGISTER_NUM];
        for(int i = 0; i < DEFAULT_REGISTER_NUM; i++) {
            registers[i] = 0;
        }

        readData(); //initialize data stuff
        setUpStack(); //stack initialization

        //this.instructor = new Instructor();
    }

    private void readData() {

    }

    private void setUpStack() {

    }



    public void execute() {
        while(Controller.PC < instructions.length * 4) {
            String instruction = fetch();
            send();
        }
    }

    public String fetch() {

        PC = PC + 4;
        return instructions[(PC - 4) / 4];

    }

    public void send() {

    }


}
