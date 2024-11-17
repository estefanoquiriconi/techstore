package com.example.frontend.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtils {
    private static final String LOCALE_LANGUAGE = "es";
    private static final String LOCALE_COUNTRY = "AR";

    private static final Locale LOCALE_AR = new Locale(LOCALE_LANGUAGE, LOCALE_COUNTRY);
    private static final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(LOCALE_AR);

    /**
     * Formatea un número a formato de moneda Argentina
     *
     * @param amount Monto a formatear
     * @return String formateado (ej: "$ 1.234,56")
     */
    public static String formatToARCurrency(Double amount) {
        return currencyFormatter.format(amount);
    }
}
