import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class PostoSaude {
    private static final int NUMERO_PESSOAS_ATENDIDAS = 100;

    public static void main(String[] args) {
        Deque<Pessoa> filaAtendimento = new ArrayDeque<>();
        Deque<Pessoa> pessoasNaoAtendidas = new ArrayDeque<>();

        // Gerar as pessoas aleatoriamente
        Random random = new Random();
        for (int i = 1; i <= NUMERO_PESSOAS_ATENDIDAS; i++) {
            String sexo = random.nextBoolean() ? "M" : "F";
            int idade = random.nextInt(80) + 1; // 1 a 80 anos
            boolean gestante = sexo.equals("F") && random.nextBoolean();
            boolean lactante = sexo.equals("F") && random.nextBoolean();
            boolean necessidadeEspecial = random.nextBoolean();

            Pessoa pessoa = new Pessoa(i, sexo, idade, gestante, lactante, necessidadeEspecial);
            filaAtendimento.addLast(pessoa);
        }

        // Atendimento das pessoas
        int grupo = 1;
        int pessoasAtendidas = 0;

        while (!filaAtendimento.isEmpty() && pessoasAtendidas < NUMERO_PESSOAS_ATENDIDAS) {
            int numeroPessoasAtendimento = Math.min(grupo, filaAtendimento.size());
            for (int i = 0; i < numeroPessoasAtendimento; i++) {
                Pessoa pessoa = filaAtendimento.removeFirst();
                if (isPrioridadeAlta(pessoa)) {
                    System.out.println("Atendimento - Pessoa: ID " + pessoa.getId() + ", Sexo " + pessoa.getSexo() +
                            ", Idade " + pessoa.getIdade() + ", Gestante " + pessoa.isGestante() + ", Lactante " + pessoa.isLactante() +
                            ", Necessidade Especial " + pessoa.isNecessidadeEspecial());
                } else {
                    pessoasNaoAtendidas.addLast(pessoa);
                }
                pessoasAtendidas++;
            }
            grupo++;
        }

        // Pessoas não atendidas
        System.out.println("\nPessoas não atendidas:");
        for (Pessoa pessoa : pessoasNaoAtendidas) {
            System.out.println("ID " + pessoa.getId() + ", Sexo " + pessoa.getSexo() + ", Idade " + pessoa.getIdade() +
                    ", Gestante " + pessoa.isGestante() + ", Lactante " + pessoa.isLactante() +
                    ", Necessidade Especial " + pessoa.isNecessidadeEspecial());
        }
    }

    private static boolean isPrioridadeAlta(Pessoa pessoa) {
        int prioridade = pessoa.getPrioridade();
        return prioridade >= 1 && prioridade <= 3;
    }
}