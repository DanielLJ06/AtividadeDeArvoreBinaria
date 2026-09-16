class Node {
    private int info;
    private Node esquerda;
    private Node direita;

    public Node(int info) {
        this.info = info;
        this.esquerda = null;
        this.direita = null;
    }

    public Node getDireita() {
        return direita;
    }

    public Node getEsquerda() {
        return esquerda;
    }

    public int getInfo() {
        return info;
    }

    public void setDireita(Node direita) {
        this.direita = direita;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }
}

class ArvoreBinaria {
    private Node raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(int novoValor) {
        if (raiz == null) {
            raiz = new Node(novoValor);
            System.out.printf("Valor %d inserido como raiz.", novoValor);
            return;
        }

        Node atual = raiz;

        while (true) {
            if (novoValor >= atual.getInfo()) {
                System.out.printf("%d >= %d -> direita", novoValor, atual.getInfo());
                if (atual.getDireita() == null) {
                    atual.setDireita(new Node(novoValor));
                    System.out.printf("Valor %d inserido a direita de %d.", novoValor, atual.getInfo());
                    return;
                } else {
                    atual = atual.getDireita();
                }
            } else {
                System.out.printf("%d < %d -> esquerda", novoValor, atual.getInfo());
                if (atual.getEsquerda() == null) {
                    atual.setEsquerda(new Node(novoValor));
                    System.out.printf("Valor %d inserido a esquerda de %d.", novoValor, atual.getInfo());
                    return;
                } else {
                    atual = atual.getEsquerda();
                }
            }
        }
    }

    public Node buscar(int elemento) {
        Node atual = raiz;
        while (atual != null && atual.getInfo() != elemento) {
            if (atual.getInfo() > elemento)
                atual = atual.getEsquerda();
            else
                atual = atual.getDireita();
        }
        return atual;
    }

    public void remover(int valor) {
        Node pai = null;
        Node atual = raiz;

        while (atual != null && atual.getInfo() != valor) {
            pai = atual;
            if (valor < atual.getInfo()) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        if (atual == null) {
            System.out.printf("Valor %d nao encontrado na arvore.", valor);
            return;
        }


        if (atual.getEsquerda() != null && atual.getDireita() != null) {
            System.out.printf("%d tem dois filhos -> buscando sucessor ", valor);

            Node paiSucessor = atual;
            Node sucessor = atual.getDireita();
            while (sucessor.getEsquerda() != null) {
                paiSucessor = sucessor;
                sucessor = sucessor.getEsquerda();
            }

            System.out.printf("Sucessor encontrado: %d. Substituindo valor de %d por %d",
                    sucessor.getInfo(), valor, sucessor.getInfo());

            atual.setInfo(sucessor.getInfo());

            pai = paiSucessor;
            atual = sucessor;
        } else {
            if (atual.getEsquerda() == null && atual.getDireita() == null) {
                System.out.printf("%d nao tem filhos (folha) -> removendo direto", valor);
            } else {
                System.out.printf("%d tem apenas um filho -> filho assume o lugar", valor);
            }
        }

        Node filho;
        if (atual.getEsquerda() != null) {
            filho = atual.getEsquerda();
        } else {
            filho = atual.getDireita();
        }

        if (pai == null) {
            raiz = filho;
            System.out.println("Raiz atualizada.");
        } else if (pai.getEsquerda() == atual) {
            pai.setEsquerda(filho);
        } else {
            pai.setDireita(filho);
        }

        System.out.printf(" Remoção de %d concluida.%n", valor);
    }


}

public class Arvore {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(3);
        arvore.inserir(7);
        arvore.inserir(12);
        arvore.inserir(20);


        System.out.println("\nBuscar o elemento 7");
        Node encontrado = arvore.buscar(7);
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado.getInfo());
        } else {
            System.out.println("Elemento nao encontrado.");
        }
        System.out.println("\nBuscar o elemento 999 (nao existe)");
        Node naoEncontrado = arvore.buscar(999);

        if (naoEncontrado != null) {
            System.out.println("Encontrado: " + naoEncontrado.getInfo());
        } else {
            System.out.println("Elemento nao encontrado.");
        }

        System.out.println("\nRemoção");
        arvore.remover(15);
        System.out.println("\n\nRemocao de valor inexistente\n\n");
        arvore.remover(3300);


    }
}
