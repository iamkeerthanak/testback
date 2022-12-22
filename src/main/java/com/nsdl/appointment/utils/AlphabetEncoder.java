package com.nsdl.appointment.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AlphabetEncoder {

	private static final char[] ALPHABET = { '0','1','2','3','4','5','6','7','8','9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final int ENCODE_LENGTH = ALPHABET.length;

    public static String encode(int victim) {
        final List<Character> list = new ArrayList<>();
        do {
            list.add(ALPHABET[victim % ENCODE_LENGTH]);
            victim /= ENCODE_LENGTH;
        } while (victim > 0);
        Collections.reverse(list);
        return list.stream().map(String::valueOf).collect(Collectors.joining());
    }

    static String getDate() {
		LocalDate date = LocalDate.now();
		StringBuilder sb = new StringBuilder(String.valueOf(date.getYear()).substring(2));
		sb.append(AlphabetEncoder.encode(date.getMonthValue()));
		sb.append(AlphabetEncoder.encode(date.getDayOfMonth()));
		return sb.toString();
	}
    
    public static String sequence(int num) {
    	String nu = AlphabetEncoder.encode(num);
    	String st = ("0000" + nu).substring(nu.length());
		return st;
    }
    
    public static String checkDigits(String val) {
		return AlphabetEncoder.encode(getTotalAsciiValue(val) % val.length());
	}
    
    public static int getTotalAsciiValue(String val) {
		return val.chars().sum();
	}
    
    public static String generateAppointmentId(int sequence) {
    	StringBuilder sb = new StringBuilder(getDate());//Date
    	sb.append(sequence(sequence));//sequence
    	return sb.toString();
    }
    
}
