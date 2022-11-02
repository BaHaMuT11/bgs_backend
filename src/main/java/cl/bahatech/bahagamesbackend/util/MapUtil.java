package cl.bahatech.bahagamesbackend.util;

import java.util.Map;

public final class MapUtil {

    private MapUtil() {
        // clase estática
    }

    /**
     * Formatea los valores de un Map en formato "key1 = 'value1', key2 = 'value'", etc..." (entre comillas)
     *
     * @param map Map para formatear los valores
     * @param <K> Tipo de dato de la Key.
     * @param <V> Tipo de dato del Value.
     * @return String con las keys y valores del Map.
     */
    public static <K, V> String keysValues(Map<K, V> map) {
        if (map.isEmpty())
            return "";

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                sb.append(String.format("%s = %s, ", String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
            } else {
                sb.append(String.format("%s = '%s', ", String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
            }
        }

        // Quita los 2 últimos caracters que corresponden a ", " agregados en for.
        return sb.toString().substring(0, sb.length() - 2);
    }

}