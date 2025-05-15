package com.isaac.ehub.domain.model;

/**
 * Modelo de datos de transferencia (DTO) para las request al backend.
 */
public class UserModel {

    private String email;
    private String password;

    private String avatarUrl;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String country;

    /**
     * Constructor para la primera fase de registro, que solo incluye email y contraseña.
     *
     * @param email Email del usuario
     * @param password Contraseña del usuario
     */
    public UserModel(String email, String password){
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor para la segunda fase de registro, que incluye el resto de propiedades.
     *
     * @param avatarUrl La URL del avatar del usuario.
     * @param firstName Nombre del usuario.
     * @param lastName Apellidos del usuario.
     * @param birthDate Fecha de nacimiento del usuario.
     * @param country Nacionalidad del usuario.
     */
    public UserModel(String avatarUrl, String firstName, String lastName, String birthDate, String country) {
        this.avatarUrl = avatarUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
