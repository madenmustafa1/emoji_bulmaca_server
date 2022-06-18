package util

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException

import com.auth0.jwt.interfaces.DecodedJWT
import java.util.*

class TokenUtil {
    private val secretKey = "very_secret_key"

    fun createToken(email: String): String? {
        val uuid = UUID.randomUUID()

        val token = try {
            val algorithm = Algorithm.HMAC256(secretKey)
            JWT.create()
                .withIssuer("auth0")
                .withClaim("email", email)
                .withClaim("uuid", uuid.toString())
                .sign(algorithm)
        } catch (exception: JWTCreationException) {
            null
        }
        return token
    }


    fun verifyToken(token: String?//, email: String
    ): Boolean{
        return try {
            val algorithm = Algorithm.HMAC256(secretKey)
            val verifier: JWTVerifier = JWT.require(algorithm)
                .withIssuer("auth0")
                //.withClaim("email", email)
                .build() //Reusable verifier instance
            val jwt: DecodedJWT = verifier.verify(token)
            true
        } catch (exception: JWTVerificationException) {
            //Invalid signature/claims
            false
        }
    }
}