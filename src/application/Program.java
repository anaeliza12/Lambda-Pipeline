package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {
	public static void main(String[] args) {

		Scanner tec = new Scanner(System.in);
		List<Employee> emps = new ArrayList<>();

		File file = new File("c:\\temp\\in.csv");

		System.out.println("Enter salary: ");
		Double sal = tec.nextDouble();

		Comparator<Employee> comp = new Comparator<Employee>() {

			@Override
			public int compare(Employee e1, Employee e2) {

				return e1.getName().toUpperCase().compareTo(e2.getName().toUpperCase());
			}
		};

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {

				String vet[] = line.split(";");
				String name = vet[0];
				String email = vet[1];
				Double salary = Double.parseDouble(vet[2]);

				Employee emp = new Employee(name, email, salary);
				
				emps.add(emp);
				line = br.readLine();

			}

			System.out.println("Email of people whose salary is more than " + sal + ": ");

			List<String> list = emps.stream().filter(x -> x.getSalary() > sal).map(x -> x.getEmail())
					.sorted((a, b) -> a.toUpperCase().compareTo(b.toUpperCase())).collect(Collectors.toList());

			list.forEach(System.out :: println);

			Double salary = emps.stream().filter(x -> x.getName().charAt(0) == 'M').map(x -> x.getSalary()).reduce(0.0,
					(a, b) -> a + b);
			System.out.print("Sum of salary of people whose name starts wit 'M': " + salary);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
