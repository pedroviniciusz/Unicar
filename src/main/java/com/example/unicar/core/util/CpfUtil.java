package com.example.unicar.core.util;

import static com.example.unicar.core.util.IsNullUtil.isNullOrEmpty;

public class CpfUtil {

	public static boolean isValido(String cpf) {
		if (isNullOrEmpty(cpf)) {
			return false;
		}

		cpf = cpf.replaceAll("\\D", "");

		if (cpf.length() != 11)
			return false;

		if(verificaDigitosRepetidos(cpf)){
			return false;
		}

		return calcularDigitos(cpf).equals(cpf.substring(9, 11));
	}

	private static boolean verificaDigitosRepetidos(String cpf)
	{
		return cpf.matches("^(\\d)\\1{10}");

	}

	private static String calcularDigitos(String cpf){
		String digitos = cpf.substring(0, 9);

		int primeiro = calcularPrimeiroDigito(digitos);
		int segundo = calcularSegundoDigito(digitos, primeiro);

		return primeiro + Integer.toString(segundo);
	}

	private static int calcularPrimeiroDigito(String digitos) {
		int primeiroDigito;
		int soma = 0;
		int peso = 10;
		for (int i = 0; i < digitos.length(); i++) {
			soma += Integer.parseInt(digitos.substring(i, i + 1)) * peso--;
		}

		if (soma % 11 == 0 | soma % 11 == 1) {
			primeiroDigito = 0;
		} else {
			primeiroDigito = 11 - (soma % 11);
		}

		return primeiroDigito;
	}

	private static int calcularSegundoDigito(String digitos, int primeiroDigito) {
		int segundoDigito;
		int soma = 0;
		int peso = 11;
		for (int i = 0; i < digitos.length(); i++) {
			soma += Integer.parseInt(digitos.substring(i, i + 1)) * peso--;
		}
		soma += primeiroDigito * 2;
		if (soma % 11 == 0 | soma % 11 == 1) {
			segundoDigito = 0;
		} else {
			segundoDigito = 11 - (soma % 11);
		}

		return segundoDigito;
	}

	public static String formatarCpf(String cpfDesformatado) {
		String cpfFormatado = cpfDesformatado.replaceAll("\\D+", "");

		return cpfFormatado.substring(0, 3) + "." + cpfFormatado.substring(3, 6) + "." + cpfFormatado.substring(6, 9) + "-" + cpfFormatado.substring(9);
	}
}
