package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Empregado;

public class Programa {

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);

    String path = "C:\\temp\\ws-eclipse\\lambda\\src\\empregados.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      List<Empregado> lista = new ArrayList<>();

      String line = br.readLine();
      while (line != null) {
        String[] fields = line.split(",");
        lista.add(new Empregado(fields[0], fields[1],
                              Double.parseDouble(fields[2])));
        line = br.readLine();
      }

      System.out.print("Entre com salário: ");
      double salario = sc.nextDouble();

      List<String> emails = lista.stream()
          .filter(x -> x.getSalario() > salario).map(x -> x.getEmail())
          .sorted().collect(Collectors.toList());

      System.out.println("Email de pessoas cujo salário é superior a "
          + String.format("%.2f", salario) + ":");
      emails.forEach(System.out::println);

      double soma = lista.stream().filter(x -> x.getNome().charAt(0) == 'J')
          .map(x -> x.getSalario()).reduce(0.0, (x, y) -> x + y);

      System.out
          .println("Soma do salário de pessoas cujo nome começa com 'J': "
              + String.format("%.2f", soma));

    } catch (IOException e) {
      System.out.println("Erro: " + e.getMessage());
    }
    sc.close();

  }

}
