package bichinhovirtual;

import java.util.InputMismatchException;
import java.util.Scanner;

public class bichinhovirtual {
    private String nome;
    private String classe;
    private String familia;
    private double idade;
    private int forca;
    private int caloria;
    private boolean vivo;

    public bichinhovirtual(String nome, String classe, String familia) {
        this.nome = nome;
        this.classe = classe;
        this.familia = familia;
        this.idade = 0;
        this.forca = 10;
        this.caloria = 10;
        this.vivo = true;
    }

    public void nascer() {
        System.out.println("====================");
        System.out.println("\nO animal se chama " + nome + ", é da classe " + classe + " e da família " + familia + ".");
        System.out.println("Possui força " + forca + ", caloria " + caloria + " e idade " + idade + ".");
    }

    public void morrer() {
        this.forca = 0;
        this.vivo = false;
        System.out.println("\n-------------------------------------------------------------------------------------------");
        System.out.println(" O animal morreu!");
        System.out.println(" " + nome + " da classe " + classe + " da família " + familia +
                           " morreu com " + forca + " de força, " + caloria + " de caloria e " + idade + " de idade.");
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    public void comer() {
        if (vivo) {
            if (caloria < 100) {
                caloria = Math.min(caloria + 10, 100);
                forca = Math.max(forca - 2, 0);
                aumentarIdade(0.5);
                System.out.println("\nO animal comeu e agora sua força é " + forca + " e suas calorias são " + caloria + ".");
            } else {
                System.out.println("\nO animal está cheio e não pode comer mais.");
            }
        } else {
            System.out.println("\nO animal está morto e não pode comer.");
        }
    }

    public void correr() {
        if (vivo) {
            if (caloria > 0) {
                forca = Math.max(forca - 5, 0);
                caloria = Math.max(caloria - 5, 0);
                aumentarIdade(0.5);
                System.out.println("\nO animal está correndo! Agora sua força é " + forca + " e suas calorias são " + caloria + ".");
            } else {
                System.out.println("\nO animal está exausto e não pode correr.");
            }
        } else {
            System.out.println("\nO animal está morto e não pode correr.");
        }
    }

    public void dormir() {
        if (vivo) {
            forca = Math.min(forca + 10, 100);
            caloria = Math.max(caloria - 2, 0);
            aumentarIdade(0.5);
            System.out.println("\nO animal está dormindo! Sua força aumentou para " + forca + " e suas calorias diminuíram para " + caloria + ".");
        } else {
            System.out.println("\nO animal está morto e não pode dormir.");
        }
    }

    private void aumentarIdade(double incremento) {
        idade += incremento;
    }

    public boolean isVivo() {
        return vivo;
    }

    public String getNome() {
        return nome;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("====================");
        System.out.println("Crie seu novo animal");
        System.out.println("====================");

        System.out.print("* Insira o nome: ");
        String nome = scanner.nextLine();

        System.out.print("* Insira a classe: ");
        String classe = scanner.nextLine();

        System.out.print("* Insira a família: ");
        String familia = scanner.nextLine();

        bichinhovirtual animal = new bichinhovirtual(nome, classe, familia);
        animal.nascer();

        while (animal.isVivo()) {
            System.out.println("\nO que " + animal.getNome() + " vai fazer agora?");
            System.out.println("1- Comer");
            System.out.println("2- Correr");
            System.out.println("3- Dormir");
            System.out.println("4- Morrer");
            System.out.print("Escolha uma opção: ");

            try {
                int escolha = scanner.nextInt();

                if (animal.forca == 0 && (escolha == 1 || escolha == 2)) {
                    System.out.println("\n" + animal.getNome() + " não tem força. Escolha dormir (3) ou morrer (4).");
                } else if (animal.caloria == 0 && (escolha == 2 || escolha == 3)) {
                    System.out.println("\n" + animal.getNome() + " está com fome. Escolha comer (1) ou morrer (4).");
                } else {
                    switch (escolha) {
                        case 1 -> animal.comer();
                        case 2 -> animal.correr();
                        case 3 -> animal.dormir();
                        case 4 -> {
                            animal.morrer();
                            System.out.println("\n ¨¨¨¨¨¨¨¨¨¨¨¨");
                            System.out.println("  GAME OVER!");
                            System.out.println(" ¨¨¨¨¨¨¨¨¨¨¨¨");
                        }
                        default -> System.out.println("\nOpção inválida! Por favor, insira um número entre 1 e 4.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\nOpção inválida! Por favor, insira um número entre 1 e 4.");
                scanner.next(); 
            }
        }
        scanner.close();
    }
}
