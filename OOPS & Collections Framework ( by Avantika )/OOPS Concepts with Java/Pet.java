// Q5: Pet, Dog, Bird program

class Pet {
    protected String name;
    protected String species;
    protected int age;

    // Constructor
    public Pet(String name, String species, int age){
        this.name = name;
        this.species = species;
        this.age = age;
    }

    // Age in human years 
    public int ageInHumanYears(){
        return age * 7;
    }

    public void display(){
        System.out.println("Name: " + name + ", Species: " + species + ", Age: " + age);
    }
}

class Dog extends Pet{
    private String favoriteToy;

    public Dog(String name, int age, String favoriteToy){
        super(name, "Dog", age);
        this.favoriteToy = favoriteToy;
    }

    @Override
    public void display(){
        super.display();
        System.out.println("Favorite Toy: " + favoriteToy);
        System.out.println("Human Age: " + ageInHumanYears());
    }
}

class Bird extends Pet{
    private double wingSpan;

    public Bird(String name, int age, double wingSpan){
        super(name, "Bird", age);
        this.wingSpan = wingSpan;
    }

    @Override
    public void display(){
        super.display();
        System.out.println("Wing Span: " + wingSpan + " cm");
        System.out.println("Human Age: " + ageInHumanYears());
    }

    public static void main(String[] args){
        
        Dog d = new Dog("Bruno", 3, "Ball");
        Bird b = new Bird("Tweety", 2, 25.5);

        d.display();
        System.out.println();
        b.display();
    }
}