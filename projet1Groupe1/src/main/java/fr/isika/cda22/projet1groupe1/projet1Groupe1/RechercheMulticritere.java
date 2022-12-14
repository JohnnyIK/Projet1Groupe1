package fr.isika.cda22.projet1groupe1.projet1Groupe1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * Classe permettant de stocker dans un objet (de classe RechercheMulticritere
 * donc) les critères de recherche et leur statut d'activation
 * 
 */
public class RechercheMulticritere implements ParametreGestionnaire {

	// Attributs -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Booleans pour chaque critere, pour determiner si un critere de recherche est
	 * activé ou pas
	 */
	private boolean nomRechSelect = false;
	private boolean prenomRechSelect = false;
	private boolean departementRechSelect = false;
	private boolean formationRechSelect = false;
	private boolean anneeFormationRechSelect = false;

	/**
	 * Les valeurs entres pour chaque critere lors d'une recherche
	 * 
	 * @nomRech Le nom qui est recherche
	 * @prenomRech Le prenom qui est recherche
	 * @departementRech La liste des departements recherches
	 * @formationRech La formation qui est recherche
	 * @anneeFormationRech L'intervale de recherche pour l'annee de formation
	 */
	private String nomRech = "";
	private String prenomRech = "";
	private List<String> departementRech = null;
	private String formationRech = "";
	private String[] anneeFormationRech = { "", "" };

	// Constructeur ----------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur de l'Objet RechercheMulticritere
	 * 
	 * @param nomRechSelect            true si le nom est recherche
	 * @param nomRech                  le nom recherche
	 * @param prenomRechSelect         true si le prenom est recherche
	 * @param prenomRech               le prenom recherche
	 * @param departementRechSelect    true si le departement est recherche
	 * @param departementRech          le departement recherche
	 * @param formationRechSelect      true si la formation est recherche
	 * @param formationRech            la formation recherche
	 * @param anneeFormationRechSelect true si l'annee est recherche
	 * @param anneeFormationRech       l'annee recherche
	 */
	public RechercheMulticritere(boolean nomRechSelect, String nomRech, boolean prenomRechSelect, String prenomRech,
			boolean departementRechSelect, List<String> departementRech, boolean formationRechSelect,
			String formationRech, boolean anneeFormationRechSelect, String[] anneeFormationRech) {
		super();
		this.nomRechSelect = nomRechSelect;
		this.prenomRechSelect = prenomRechSelect;
		this.departementRechSelect = departementRechSelect;
		this.formationRechSelect = formationRechSelect;
		this.anneeFormationRechSelect = anneeFormationRechSelect;
		this.nomRech = nomRech;
		this.prenomRech = prenomRech;
		this.departementRech = departementRech;
		this.formationRech = formationRech;
		this.anneeFormationRech = anneeFormationRech;
	}

	// Getters and setters -----------------------------------------------------------------------------------------------------------------------------
	public void setNomRechSelect(boolean nomRechSelect) {
		this.nomRechSelect = nomRechSelect;
	}

	public void setPrenomRechSelect(boolean prenomRechSelect) {
		this.prenomRechSelect = prenomRechSelect;
	}

	public void setDepartementRechSelect(boolean departementRechSelect) {
		this.departementRechSelect = departementRechSelect;
	}

	public void setFormationRechSelect(boolean formationRechSelect) {
		this.formationRechSelect = formationRechSelect;
	}

	public void setAnneeFormationRechSelect(boolean anneeFormationRechSelect) {
		this.anneeFormationRechSelect = anneeFormationRechSelect;
	}

	public String getNomRech() {
		return nomRech;
	}

	public void setNomRech(String nomRech) {
		this.nomRech = nomRech;
	}

	public String getPrenomRech() {
		return prenomRech;
	}

	public void setPrenomRech(String prenomRech) {
		this.prenomRech = prenomRech;
	}

	public List<String> getDepartementRech() {
		return departementRech;
	}

	public void setDepartementRech(List<String> departementRech) {
		this.departementRech = departementRech;
	}

	public String getFormationRech() {
		return formationRech;
	}

	public void setFormationRech(String formationRech) {
		this.formationRech = formationRech;
	}

	public String[] getAnneeFormationRech() {
		return anneeFormationRech;
	}

	public void setAnneeFormationRech(String[] anneeFormationRech) {
		this.anneeFormationRech = anneeFormationRech;
	}

    //METHODES ---------------------------------------------------------------------------------------------------------------------------
	/**
	 * Retourne true si le nom est recherche (false sinon)
	 * 
	 * @return true si le nom est recherche (false sinon)
	 */
	public boolean isNomRechSelect() {
		return nomRechSelect;
	}

	/**
	 * Retourne true si le prenom est recherche (false sinon)
	 * 
	 * @return true si le prenom est recherche (false sinon)
	 */
	public boolean isPrenomRechSelect() {
		return prenomRechSelect;
	}

	/**
	 * Retourne true si le departement est recherche (false sinon)
	 * 
	 * @return true si le departement est recherche (false sinon)
	 */
	public boolean isDepartementRechSelect() {
		return departementRechSelect;
	}

	/**
	 * Retourne true si la formation est recherche (false sinon)
	 * 
	 * @return true si la formation est recherche (false sinon)
	 */
	public boolean isFormationRechSelect() {
		return formationRechSelect;
	}

	/**
	 * Retourne true si l'annee est recherche (false sinon)
	 * 
	 * @return true si l'annee est recherche (false sinon)
	 */
	public boolean isAnneeFormationRechSelect() {
		return anneeFormationRechSelect;
	}

	/**
	 * Compare les valeurs du noeud en argument avec les criteres de recherche et
	 * retourne "true" si l'un des criteres est valide, "false" sinon
	 * 
	 * @param noeud Le noeud sur lequel on va faire la recherche
	 * @return "true" si une des valeurs du noeud correspond à l'une des valeurs
	 *         recherchees
	 */
	public Boolean rechercheMulticritereNoeud(Noeud noeud) {
		// on recupere les attributs du noeud a l'index "index" : nom / prenom /
		// departement / formation / anneeFormation
		String nomNoeud = noeud.getCle().getNom();
		String prenomNoeud = noeud.getCle().getPrenom();
		String departementNoeud = noeud.getCle().getDepartement();
		String formationNoeud = noeud.getCle().getFormation();
		String anneeFormationNoeud = noeud.getCle().getAnneeFormation();

		// on lance les comparaisons :
		Boolean nomPresent = true;
		Boolean prenomPresent = true;
		Boolean departementPresent = true;
		Boolean formationPresent = true;
		Boolean anneeFormationPresent = true;

		if (this.nomRechSelect) {
			// nomPresent = nomNoeud.contains(this.nomRech);
			nomPresent = nomNoeud.startsWith(this.nomRech);
		}
		if (this.prenomRechSelect) {
			// prenomPresent = prenomNoeud.contains(this.prenomRech);
			prenomPresent = prenomNoeud.startsWith(this.prenomRech);
		}
		if (this.departementRechSelect) {
			// if (this.departementRech.get(0).length() == 1) {
			departementPresent = departementNoeud.contains(this.departementRech.get(0));
			System.out.println("departement entré " + this.departementRech.get(0));
			// }
			// departementPresent = departementIsContained(departementNoeud,
			// this.departementRech);
		}
		if (this.formationRechSelect) {
			// formationPresent = formationNoeud.contains(this.formationRech);
			formationPresent = formationNoeud.startsWith(this.formationRech);
		}
		if (this.anneeFormationRechSelect) {
			anneeFormationPresent = anneeFormationIsContained(anneeFormationNoeud, this.anneeFormationRech);
		}

		// si une des comparaisons est vraie, on valide le test de recherche
		if (nomPresent && prenomPresent && departementPresent && formationPresent && anneeFormationPresent) {
			return true;
		} else { // si toutes les comparaisons sont fausses, on invalide le test de rechercher
			return false;
		}
	}

	/**
	 * Determine si un département fait partie d'une liste de departements donnee
	 * 
	 * @param departementNoeud Le departement a tester
	 * @param departementRech  La liste des départements dans lequel on recherche
	 * @return Boolean true si le département a tester est présent dans la liste des
	 *         departements / False sinon
	 */
	private Boolean departementIsContained(String departement, List<String> departementRech) {
		for (String departementRecherche : departementRech) {
			if (departement.contains(departementRecherche)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determine si une annee est incluse dans un intervale [annee X, annee Y]
	 * 
	 * @param anneeFormation     L'annee a tester
	 * @param anneeFormationRech Le tableau [annee X, annee Y] contenant les bornes
	 *                           de l'intervale dans lequel on recherche
	 * @return Boolean true si l'annee a tester est presente dans l'intervale donné
	 *         / False sinon
	 */
	private Boolean anneeFormationIsContained(String anneeFormation, String[] anneeFormationRech) {
		int yearlowerRange = Integer.parseInt(anneeFormationRech[0]);
		int yearUpperRange = Integer.parseInt(anneeFormationRech[1]);
		int anneeFormationInt = Integer.parseInt(anneeFormation);
		if ((anneeFormationInt >= yearlowerRange) && (anneeFormationInt <= yearUpperRange)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * (Methode optionnelle) Compare les valeurs du noeud en argument avec les
	 * criteres de recherche entres en argument et retourne "true" si l'un des
	 * criteres est valide, "false" sinon
	 * 
	 * @param noeud                    Le noeud sur lequel on va faire la recherche
	 * @param nomRechSelect            "true" si la recherche par nom est activee
	 * @param nomRech                  Le nom qui est recherche
	 * @param prenomRechSelect         "true" si la recherche par prenom est activee
	 * @param prenomRech               Le prenom qui est recherche
	 * @param departementRechSelect    "true" si la recherche par departement est
	 *                                 activee
	 * @param departementRech          Le departement qui est recherche
	 * @param formationRechSelect      "true" si la recherche par formation est
	 *                                 activee
	 * @param formationRech            La formation qui est recherchee
	 * @param anneeFormationRechSelect "true" si la recherche par annee de formation
	 *                                 est activee
	 * @param anneeFormationRech       L'annee de formation qui est recherchee
	 * @return "true" si une des valeurs du noeud correspond à l'une des valeurs
	 *         recherchees
	 */
	public Boolean rechercheMulticritereNoeudCriteres(Noeud noeud, boolean nomRechSelect, String nomRech,
			boolean prenomRechSelect, String prenomRech, boolean departementRechSelect, List<String> departementRech,
			boolean formationRechSelect, String formationRech, boolean anneeFormationRechSelect,
			String[] anneeFormationRech) {
		// on recupere les attributs du noeud a l'index "index" : nom / prenom /
		// departement / formation / anneeFormation
		String nomNoeud = noeud.getCle().getNom();
		String prenomNoeud = noeud.getCle().getPrenom();
		String departementNoeud = noeud.getCle().getDepartement();
		String formationNoeud = noeud.getCle().getFormation();
		String anneeFormationNoeud = noeud.getCle().getAnneeFormation();

		// on lance les comparaisons :
		Boolean nomPresent = false;
		Boolean prenomPresent = false;
		Boolean departementPresent = false;
		Boolean formationPresent = false;
		Boolean anneeFormationPresent = false;

		if (nomRechSelect) {
			nomPresent = nomNoeud.contains(nomRech);
		}
		if (prenomRechSelect) {
			prenomPresent = prenomNoeud.contains(prenomRech);
		}
		if (departementRechSelect) {
			departementPresent = departementIsContained(departementNoeud, departementRech);
		}
		if (formationRechSelect) {
			formationPresent = formationNoeud.contains(formationRech);
		}
		if (anneeFormationRechSelect) {
			anneeFormationPresent = anneeFormationIsContained(anneeFormationNoeud, anneeFormationRech);
		}

		// si une des comparaisons est vraie, on valide le test de recherche
		if (nomPresent || prenomPresent || departementPresent || formationPresent || anneeFormationPresent) {
			return true;
		} else { // si toutes les comparaisons sont fausses, on invalide le test de rechercher
			return false;
		}
	}

}
