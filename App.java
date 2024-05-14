public class App {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> teste = new HashTable<>(17);
        teste.put("Amanda", 1);
        teste.put("Julia", 1);
        teste.put("Patricia", 2);
        teste.put("Jose", 3);
        teste.put("Joao", 4);
        teste.put("Lucas", 6);
        System.out.println(teste);
        System.out.println("removendo " + teste.delete("Amanda"));
        System.out.println("removendo " + teste.delete("Julia"));
        System.out.println("removendo " + teste.delete("Patricia"));
        System.out.println(teste);
        
        
    }
}
