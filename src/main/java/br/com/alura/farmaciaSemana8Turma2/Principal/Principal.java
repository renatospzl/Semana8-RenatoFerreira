package br.com.alura.farmaciaSemana8Turma2.Principal;

import br.com.alura.farmaciaSemana8Turma2.model.Fabricante;
import br.com.alura.farmaciaSemana8Turma2.model.Produto;
import br.com.alura.farmaciaSemana8Turma2.repository.FabricanteRepository;
import br.com.alura.farmaciaSemana8Turma2.repository.ProdutoRepository;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private ProdutoRepository produtoRepository;
    private FabricanteRepository fabricanteRepository;
    private Scanner leitura = new Scanner(System.in);

    public Principal(ProdutoRepository produtoRepository, FabricanteRepository fabricanteRepository) {
        this.produtoRepository = produtoRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    public void exibeMenu(){
        var opcao = -1;

        while (opcao != 9){
            var menu = """
                    *** Farmacia ***
                    
                    1- Cadastrar produto
                    2- Listar produto
                    3- Editar produto
                    4- Deletar produto
                    
                    9- Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProduto();
                    break;
                case 3:
                    editarProduto();
                    break;
                case 4:
                    deletarProduto();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    private void cadastrarProduto(){
        System.out.println("Informe o nome do Fabricante: ");
        var nomeFabricante = leitura.nextLine();
        var fabricante = new Fabricante(nomeFabricante);
        fabricanteRepository.save(fabricante);

        System.out.println("Informe o nome do Produto: ");
        var nomeProduto = leitura.nextLine();

        System.out.println("Informe a descrição do produto: ");
        var descricaoProduto = leitura.nextLine();

        System.out.println("Informe o preço do produto: ");
        var precoProduto = leitura.nextDouble();

        var produto = new Produto(nomeProduto, descricaoProduto, precoProduto, fabricante);
        produtoRepository.save(produto);
        System.out.println("Produto Cadastrado com Sucesso!!! \n");

    }

    private void listarProduto(){
        var produtos = produtoRepository.findAll();
        produtos.forEach(System.out::println);

    }

    private void editarProduto(){
        System.out.println("Informe o Id do produto para ser editado: ");
        var id = leitura.nextInt();
        leitura.nextLine();

        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()){
            System.out.println("Produto não encontrado!!! \n");
        }else {
            System.out.println("Informe a descrição do produto para ser atualizado: ");
            var descricaoProduto = leitura.nextLine();
            var produto = produtoOptional.get();
            produto.setDescricao(descricaoProduto);
            produtoRepository.save(produto);
            System.out.println("Produto Atualizado com Sucesso!!! \n");
        }


    }

    private void deletarProduto(){
        System.out.println("Informe o Id do produto para deletar: ");
        var id = leitura.nextInt();
        leitura.nextLine();

        var produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()){
            System.out.println("Produto não encontrado!!! \n");
        }else {
            produtoRepository.deleteById(id);
            System.out.println("Produto Apagado com Sucesso!!! \n");
        }

    }

}
