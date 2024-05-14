import java.util.Arrays;

public class HashTable<K,V> implements Map<K,V>{
    int size;
    Node<Entry<K,V>>[] hashTable;                               //cria um vetor de nós


    public HashTable(int maxMap) {
        hashTable = new Node[maxMap];                           //maxMap == tamanho do espaço
    }

    @Override
    public Entry<K, V> delete(K key) {
        int index = hashFunction(key);
        Node<Entry<K,V>> auxNode = getByIndex(index, key);
        
        if(auxNode == null){return null;}

        if(auxNode.previous == null && auxNode.next == null){   //remove quando so tem um
            hashTable[index] = null;
        }
        else if(auxNode.previous == null){
            hashTable[index] = auxNode.next;                    //remove o do comeco
            hashTable[index].previous = null; 
        }
        else if(auxNode.next == null){
            hashTable[index].previous.next = null;              //remove o ultimo
        }
        else{
            auxNode.previous.next = auxNode.next;
            auxNode.next.previous = auxNode.previous;           //remove o do meio
        }
        size--;
        return auxNode.entry;
    }

    @Override
    public Entry<K, V> get(K key) {
        if(isEmpty()){
            throw new RuntimeException ("HashTable is Empty");
        }
        int index = hashFunction(key);
        return getByIndex(index, key).entry;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    public Node<Entry<K,V>> getByIndex(int index, K key){
        Node<Entry<K,V>> auxNode = hashTable[index];

        while(auxNode != null){
            if(auxNode.entry.getKey().equals(key)){
                return auxNode;
            }
            auxNode = auxNode.next;
        }
        return auxNode;
    }
    
    @Override
    public void put(K key, V value) {
        int index = hashFunction(key);
        Entry<K,V> entry = new HashEntry<>(key, value);
        Node<Entry<K,V>> newNode = new Node<>(entry);

        if(hashTable[index] == null){
            hashTable[index] = newNode;
        }
        else{
            if(hashTable[index] != null){
                Node<Entry<K,V>> auxNode = hashTable[index];

                while(auxNode.entry.getKey().equals(key)){
                    auxNode.entry.setValue(value);
                }
                auxNode = auxNode.next;
            }else{
                hashTable[index].previous = newNode;
                newNode.next = hashTable[index];
                hashTable[index] = newNode;
            }
        }

    }

    @Override
    public int size() {
        return size;
    }

    private int hashCode(String key){
        int hashCode = 0;
        int a = 5;
        for(int i = 0; i < key.length(); i++){
            hashCode = (hashCode<<a) | (hashCode>>>(32-a));
            hashCode += key.charAt(i);
        }
        return hashCode;
    }

    private int compression(int hashCode){
        int comp = hashCode%hashTable.length;
        if(comp<0){
            comp*=-1;
        }
        return comp;
    }

    private int hashFunction(K key){
        if(key instanceof String){
            return compression(hashCode(((String)key)));
        }
        throw new RuntimeException("HashCode does not support that data type (YET!)");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        for(int i=0; i<hashTable.length; i++){
            sb.append(i);
            sb.append(":");

            Node<Entry<K,V>> auxNode = hashTable[i];
            while(auxNode != null){
                sb.append("\n ");
                sb.append(auxNode.entry);
                auxNode = auxNode.next;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    

    

}
