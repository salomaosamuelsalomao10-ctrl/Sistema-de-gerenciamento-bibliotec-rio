import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static final int MAX_LIVROS = 200;
    static final int MAX_UTILIZADORES = 200;

    // DADOS DOS LIVROS
    static int[] idLivro = new int[MAX_LIVROS];
    static String[] titulo = new String[MAX_LIVROS];
    static String[] autor = new String[MAX_LIVROS];
    static int[] ano = new int[MAX_LIVROS];
    static int[] quantidade = new int[MAX_LIVROS];
    static String[] linkLeitura = new String[MAX_LIVROS];
    static int[] totalEmprestimos = new int[MAX_LIVROS];

    static int numeroLivros = 0;

    // DADOS DOS UTILIZADORES
    static int[] idUtilizador = new int[MAX_UTILIZADORES];
    static int numeroUtilizadores = 0;

    // MATRIZ DE EMPRÉSTIMOS
    static int[][] emprestimos =
            new int[MAX_UTILIZADORES][MAX_LIVROS];


    public static void main(String[] args) {

        int opcao;

        do {

            mostrarMenu();

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {

                case 1:
                    registarLivro();
                    break;

                case 2:
                    consultarCatalogo();
                    break;

                case 3:
                    pesquisarLivro();
                    break;

                case 4:
                    registarUtilizador();
                    break;

                case 5:
                    efectuarEmprestimo();
                    break;

                case 6:
                    efectuarDevolucao();
                    break;

                case 7:
                    mostrarEstatisticas();
                    break;

                case 8:
                    lerLivroOnline();
                    break;

                case 0:
                    System.out.println();
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println();
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }


    // ==============================
    // MENU
    // ==============================

    static void mostrarMenu() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("       GERENCIAMENTO DE BIBLIOTECA");
        System.out.println("==========================================");
        System.out.println("1 - Registar livro");
        System.out.println("2 - Consultar catálogo");
        System.out.println("3 - Pesquisar livro");
        System.out.println("4 - Registar utilizador");
        System.out.println("5 - Efectuar empréstimo");
        System.out.println("6 - Efectuar devolução");
        System.out.println("7 - Estatísticas");
        System.out.println("8 - Ler livro online");
        System.out.println("0 - Sair");
        System.out.println("==========================================");
    }


    // ==============================
    // REGISTAR LIVRO
    // ==============================

    static void registarLivro() {

        System.out.println();
        System.out.println("========== REGISTAR LIVRO ==========");

        if (numeroLivros >= MAX_LIVROS) {

            System.out.println("Limite de livros atingido.");
            return;
        }

        int id = lerInteiro("ID do livro: ");

        if (procurarLivro(id) != -1) {

            System.out.println("Erro: este ID já existe.");
            return;
        }

        System.out.print("Título: ");
        String novoTitulo = scanner.nextLine();

        if (novoTitulo.trim().isEmpty()) {

            System.out.println("O título não pode ficar vazio.");
            return;
        }

        System.out.print("Autor: ");
        String novoAutor = scanner.nextLine();

        if (novoAutor.trim().isEmpty()) {

            System.out.println("O autor não pode ficar vazio.");
            return;
        }

        int novoAno =
                lerInteiro("Ano de publicação: ");

        int novaQuantidade =
                lerInteiro("Quantidade disponível: ");

        if (novaQuantidade < 0) {

            System.out.println(
                    "A quantidade não pode ser negativa."
            );

            return;
        }

        System.out.print("Link para leitura online: ");
        String novoLink = scanner.nextLine();

        idLivro[numeroLivros] = id;
        titulo[numeroLivros] = novoTitulo;
        autor[numeroLivros] = novoAutor;
        ano[numeroLivros] = novoAno;
        quantidade[numeroLivros] = novaQuantidade;
        linkLeitura[numeroLivros] = novoLink;
        totalEmprestimos[numeroLivros] = 0;

        numeroLivros++;

        System.out.println();
        System.out.println("Livro registado com sucesso!");
    }


    // ==============================
    // CONSULTAR CATÁLOGO
    // ==============================

    static void consultarCatalogo() {

        System.out.println();
        System.out.println("============= CATÁLOGO =============");

        if (numeroLivros == 0) {

            System.out.println(
                    "Não existem livros registados."
            );

            return;
        }

        for (int i = 0; i < numeroLivros; i++) {

            System.out.println();
            System.out.println("ID: " + idLivro[i]);
            System.out.println("Título: " + titulo[i]);
            System.out.println("Autor: " + autor[i]);
            System.out.println(
                    "Ano de publicação: " + ano[i]
            );
            System.out.println(
                    "Quantidade disponível: "
                    + quantidade[i]
            );

            System.out.println(
                    "Leitura online: "
                    + (linkLeitura[i].isEmpty()
                    ? "Não disponível"
                    : "Disponível")
            );

            System.out.println(
                    "-----------------------------------"
            );
        }
    }


    // ==============================
    // PESQUISAR LIVRO
    // ==============================

    static void pesquisarLivro() {

        System.out.println();
        System.out.println("=========== PESQUISAR ===========");

        if (numeroLivros == 0) {

            System.out.println(
                    "Não existem livros registados."
            );

            return;
        }

        System.out.print(
                "Digite o título ou autor: "
        );

        String pesquisa =
                scanner.nextLine().toLowerCase();

        boolean encontrado = false;

        for (int i = 0; i < numeroLivros; i++) {

            if (
                titulo[i].toLowerCase()
                        .contains(pesquisa)
                ||
                autor[i].toLowerCase()
                        .contains(pesquisa)
            ) {

                System.out.println();
                System.out.println("ID: " + idLivro[i]);
                System.out.println(
                        "Título: " + titulo[i]
                );
                System.out.println(
                        "Autor: " + autor[i]
                );
                System.out.println(
                        "Ano: " + ano[i]
                );
                System.out.println(
                        "Quantidade: " + quantidade[i]
                );

                encontrado = true;
            }
        }

        if (!encontrado) {

            System.out.println();
            System.out.println(
                    "Nenhum livro encontrado."
            );
        }
    }


    // ==============================
    // REGISTAR UTILIZADOR
    // ==============================

    static void registarUtilizador() {

        System.out.println();
        System.out.println(
                "======= REGISTAR UTILIZADOR ======="
        );

        if (numeroUtilizadores >= MAX_UTILIZADORES) {

            System.out.println(
                    "Limite de utilizadores atingido."
            );

            return;
        }

        int id = lerInteiro(
                "ID do utilizador: "
        );

        if (procurarUtilizador(id) != -1) {

            System.out.println(
                    "Erro: este ID já existe."
            );

            return;
        }

        idUtilizador[numeroUtilizadores] = id;

        numeroUtilizadores++;

        System.out.println();
        System.out.println(
                "Utilizador registado com sucesso!"
        );
    }


    // ==============================
    // EMPRÉSTIMO
    // ==============================

    static void efectuarEmprestimo() {

        System.out.println();
        System.out.println(
                "========== EMPRÉSTIMO =========="
        );

        if (numeroLivros == 0) {

            System.out.println(
                    "Não existem livros registados."
            );

            return;
        }

        if (numeroUtilizadores == 0) {

            System.out.println(
                    "Não existem utilizadores registados."
            );

            return;
        }

        int idU =
                lerInteiro("ID do utilizador: ");

        int posU =
                procurarUtilizador(idU);

        if (posU == -1) {

            System.out.println(
                    "Utilizador não encontrado."
            );

            return;
        }

        int idL =
                lerInteiro("ID do livro: ");

        int posL =
                procurarLivro(idL);

        if (posL == -1) {

            System.out.println(
                    "Livro não encontrado."
            );

            return;
        }

        if (quantidade[posL] <= 0) {

            System.out.println(
                    "Livro indisponível."
            );

            return;
        }

        quantidade[posL]--;

        emprestimos[posU][posL]++;

        totalEmprestimos[posL]++;

        System.out.println();
        System.out.println(
                "Empréstimo efectuado com sucesso!"
        );
    }


    // ==============================
    // DEVOLUÇÃO
    // ==============================

    static void efectuarDevolucao() {

        System.out.println();
        System.out.println(
                "========== DEVOLUÇÃO =========="
        );

        if (numeroUtilizadores == 0) {

            System.out.println(
                    "Não existem utilizadores registados."
            );

            return;
        }

        if (numeroLivros == 0) {

            System.out.println(
                    "Não existem livros registados."
            );

            return;
        }

        int idU =
                lerInteiro("ID do utilizador: ");

        int posU =
                procurarUtilizador(idU);

        if (posU == -1) {

            System.out.println(
                    "Utilizador não encontrado."
            );

            return;
        }

        int idL =
                lerInteiro("ID do livro: ");

        int posL =
                procurarLivro(idL);

        if (posL == -1) {

            System.out.println(
                    "Livro não encontrado."
            );

            return;
        }

        if (emprestimos[posU][posL] <= 0) {

            System.out.println(
                    "Este utilizador não possui "
                    + "este livro emprestado."
            );

            return;
        }

        emprestimos[posU][posL]--;

        quantidade[posL]++;

        System.out.println();
        System.out.println(
                "Devolução efectuada com sucesso!"
        );
    }


    // ==============================
    // ESTATÍSTICAS
    // ==============================

    static void mostrarEstatisticas() {

        System.out.println();
        System.out.println(
                "========== ESTATÍSTICAS =========="
        );

        int totalRequisitados = 0;
        int maiorNumero = 0;
        int livroMaisEmprestado = -1;

        for (int i = 0; i < numeroLivros; i++) {

            totalRequisitados +=
                    totalEmprestimos[i];

            if (
                    totalEmprestimos[i]
                            > maiorNumero
            ) {

                maiorNumero =
                        totalEmprestimos[i];

                livroMaisEmprestado = i;
            }
        }

        System.out.println();
        System.out.println(
                "Total de livros registados: "
                + numeroLivros
        );

        System.out.println(
                "Total de utilizadores registados: "
                + numeroUtilizadores
        );

        System.out.println(
                "Total de livros requisitados: "
                + totalRequisitados
        );

        if (livroMaisEmprestado != -1) {

            System.out.println();

            System.out.println(
                    "Livro mais emprestado: "
                    + titulo[livroMaisEmprestado]
            );

            System.out.println(
                    "Número de empréstimos: "
                    + maiorNumero
            );

        } else {

            System.out.println();

            System.out.println(
                    "Ainda não foram efectuados empréstimos."
            );
        }
    }


    // ==============================
    // LEITURA ONLINE
    // ==============================

    static void lerLivroOnline() {

        System.out.println();
        System.out.println(
                "========= LEITURA ONLINE ========="
        );

        if (numeroLivros == 0) {

            System.out.println(
                    "Não existem livros registados."
            );

            return;
        }

        for (int i = 0; i < numeroLivros; i++) {

            System.out.println(
                    (i + 1)
                    + " - "
                    + titulo[i]
                    + " - "
                    + autor[i]
            );
        }

        System.out.println();

        int escolha =
                lerInteiro(
                        "Escolha o número do livro: "
                );

        if (
                escolha < 1
                ||
                escolha > numeroLivros
        ) {

            System.out.println(
                    "Livro inválido."
            );

            return;
        }

        int posicao = escolha - 1;

        System.out.println();
        System.out.println(
                "=================================="
        );

        System.out.println(
                "Título: " + titulo[posicao]
        );

        System.out.println(
                "Autor: " + autor[posicao]
        );

        System.out.println(
                "Ano: " + ano[posicao]
        );

        System.out.println(
                "=================================="
        );

        System.out.println();

        if (
                linkLeitura[posicao] == null
                ||
                linkLeitura[posicao]
                        .trim()
                        .isEmpty()
        ) {

            System.out.println(
                    "Este livro não possui "
                    + "link de leitura online."
            );

            return;
        }

        System.out.println(
                "Link para leitura online:"
        );

        System.out.println();

        System.out.println(
                linkLeitura[posicao]
        );

        System.out.println();

        System.out.println(
                "Copie o link e abra-o "
                + "no navegador."
        );
    }


    // ==============================
    // PROCURAR LIVRO
    // ==============================

    static int procurarLivro(int id) {

        for (int i = 0; i < numeroLivros; i++) {

            if (idLivro[i] == id) {

                return i;
            }
        }

        return -1;
    }


    // ==============================
    // PROCURAR UTILIZADOR
    // ==============================

    static int procurarUtilizador(int id) {

        for (
                int i = 0;
                i < numeroUtilizadores;
                i++
        ) {

            if (idUtilizador[i] == id) {

                return i;
            }
        }

        return -1;
    }


    // ==============================
    // LER NÚMERO
    // ==============================

    static int lerInteiro(String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                return Integer.parseInt(
                        scanner
                                .nextLine()
                                .trim()
                );

            } catch (
                    NumberFormatException e
            ) {

                System.out.println(
                        "Digite um número válido."
                );
            }
        }
    }
          }
