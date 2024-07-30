package fr.diginamic.jdbc.entites;

public class Article {
    private int id;
    private String designation;
    private double prix;
    private Fournisseur fournisseur;
    private String ref;

    public Article(int id, String designation, double prix, Fournisseur fournisseur) {
        this.id = id;
        this.designation = designation;
        this.prix = prix;
        this.fournisseur = fournisseur;
        this.ref = ref;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", prix=" + prix +
                ", fournisseur=" + fournisseur +
                ", ref='" + ref + '\'' +
                '}';
    }
}