package fr.diginamic.jdbc.entites;

public class Article {
    private int id;
    private String ref;
    private String designation;
    private double prix;
    private Fournisseur fournisseur;

    public Article(int id, String ref, String designation, double prix, Fournisseur fournisseur) {
        this.id = id;
        this.ref = ref;
        this.designation = designation;
        this.prix = prix;
        this.fournisseur = fournisseur;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
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

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", designation='" + designation + '\'' +
                ", prix=" + prix +
                ", fournisseur=" + fournisseur +
                '}';
    }
}