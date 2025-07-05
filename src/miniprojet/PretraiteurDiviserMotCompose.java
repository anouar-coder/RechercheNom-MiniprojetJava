package miniprojet;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Prétraiteur qui divise les mots composés en leurs composants et normalise en minuscules.
 */
public class PretraiteurDiviserMotCompose implements Pretraiteur {
    @Override
    public List<Nom> traiter(List<Nom> noms) {
        return noms.stream()
                .map(nom -> new Nom(splitCompoundName(nom.getValeur())))
                .collect(Collectors.toList());
    }

    /**
     * Divise un nom composé en ses composants et normalise.
     *
     * @param name Nom d'entrée.
     * @return Nom divisé et normalisé.
     */
    private String splitCompoundName(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        // Diviser sur les tirets, espaces, apostrophes
        String[] parts = name.split("[\\s'-]+");
        // Joindre les parties avec un seul espace et normaliser
        return String.join(" ", parts).toLowerCase().trim();
    }
}