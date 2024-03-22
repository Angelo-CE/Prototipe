package com.example.prototipe

import android.widget.EditText

import java.text.SimpleDateFormat
import java.util.*

class VerifyData {
    companion object {
        fun validateRegister(data: List<EditText>): List<String> {
            val msgError = mutableListOf<String>()

            if (!validatePersonalId(data[0].text.toString())) {
                msgError.add("La cédula debe tener 9 números.")
            }

            if (!validateNameAndLast(data[1].text.toString(), data[2].text.toString())) {
                msgError.add("Nombre o apellido incorrecto.")
            }

            if (!validateAddress(data[3].text.toString())) {
                msgError.add("La dirección debe tener entre 5 y 20 caracteres.")
            }

            if(!validateBirthday(data[4].text.toString(),data[5].text.toString(),data[6].text.toString())){
                val day = data[4].text.toString()
                val month = data[5].text.toString()
                val year = data[6].text.toString()
                msgError.add("La fecha '$day/$month/$year' esta en un formato incorrecto")
            }

            if(!validatePhone(data[7].text.toString())){
                msgError.add("La cantidad de numeros no es correcta")
            }

            if(!validateEmail(data[8].text.toString())){
                val email = data[8].text.toString()
                msgError.add("El correo '$email' tiene un formato incorrecto")
            }

            if(!validatePassword(data[9].text.toString())){
                msgError.add("Tamaño de la contraseña incorrecto")
            }

            if(!validateConfirmpassword(data[9].text.toString(),data[10].text.toString())){
                msgError.add("Las contraseñas no son iguales")
            }

            return msgError
        }

        private fun validatePersonalId(id: String): Boolean {
            return id.length == 9 && id.all { it.isDigit() }
        }

        private fun validateNameAndLast(name: String, lastName: String): Boolean {
            return name.length in 2..16 && lastName.length in 2..16
        }

        private fun validateAddress(address: String): Boolean {
            return address.length in 5..20
        }

        private fun validateBirthday(day:String, month:String, year:String): Boolean{
            val birthdayStr = "$day/$month/$year"
            val dateFormat = SimpleDateFormat("dd/MM/yyyy",Locale.getDefault())

            try{
                val birthday = dateFormat.parse(birthdayStr)
                val actualdate = Date()

                return birthday?.before(actualdate)?:false
            }catch (e:Exception){
                return false
            }

        }

        fun validatePhone(phone: String): Boolean {
            // Verificar si el número de teléfono tiene exactamente 10 dígitos y todos son numéricos
            return phone.length == 8 && phone.all { it.isDigit() }
        }

        fun validateEmail(email: String): Boolean {
            // Verificar si el correo tiene un formato válido utilizando una expresión regular
            val regex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
            return regex.matches(email)
        }

        fun validatePassword(password: String): Boolean {
            // Verificar si la contraseña tiene al menos 8 caracteres
            return password.length in 8..16
        }

        fun validateConfirmpassword(contrasena: String, confirm: String): Boolean {
            // Verificar si la contraseña de confirmación coincide con la contraseña original
            return contrasena == confirm
        }
    }
}