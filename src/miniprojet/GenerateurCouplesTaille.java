package miniprojet;

import java.util.*;

/**
 * Générateur de candidats qui associe des noms de longueurs similaires.
 */
public class GenerateurCouplesTaille implements GenerateurCandidats {
    @Override
    public List<CoupleNom> genererCandidats(List<Nom> listeRecherche, List<Nom> listeBase) {
        // Grouper les noms de listeBase par taille
        Map<Integer, List<Nom>> tailleANoms = new HashMap<>();
        for (Nom nom : listeBase) {
            int taille = nom.getValeur().length();
            tailleANoms.computeIfAbsent(taille, k -> new ArrayList<>()).add(nom);
        }

        // Générer des paires en associant chaque nom de listeRecherche
        // aux noms de listeBase ayant une taille similaire (±1)
        List<CoupleNom> couples = new ArrayList<>();
        for (Nom nom1 : listeRecherche) {
            int taille = nom1.getValeur().length();
            // Considérer les tailles identiques et adjacentes
            for (int delta = -1; delta <= 1; delta++) {
                int tailleCible = taille + delta;
                List<Nom> candidats = tailleANoms.getOrDefault(tailleCible, Collections.emptyList());
                for (Nom nom2 : candidats) {
                    couples.add(new CoupleNom(nom1, nom2));
                }
            }
        }

        return couples;
    }
}