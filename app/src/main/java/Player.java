public class Player {
    private String name;
    private int HP;

    public Player(String name, int HP) {
        this.name = name;
        this.HP = HP;
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public int getHP() {
        return HP;
    }

    public Player setHP(int HP) {
        this.HP = HP;
        return this;
    }

    public boolean attack(int power){
        int newHP = this.getHP()-power;
        if(newHP>0){
            this.setHP(newHP);
            return true;
        }
        return false;
    }
}
