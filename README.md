Persistent Mobile Foundation
===
## JavaTokenValidator
A sample application demonstrating use of the Java Token Validator filter.

### Tutorials
https://pmf.persistentproducts.com/tutorials/en/foundation/9.0/application-development/authentication-and-security/protecting-external-resources/

### Usage

You can deploy the project on supported application servers (Tomcat, WebSphere Full profile and WebSphere Liberty profile).  

This sample can be used in coordination with client samples applications described here:

- [UserLogin](https://pmf.persistentproducts.com/tutorials/en/foundation/9.0/application-development/authentication-and-security/user-authentication/security-check/)
- [PinCodeAttempts](https://pmf.persistentproducts.com/tutorials/en/foundation/9.0/application-development/authentication-and-security/credentials-validation/security-check/)

### Sample usage

1. Make sure to [update the confidential client](https://pmf.persistentproducts.com/tutorials/en/foundation/9.0/application-development/authentication-and-security/protecting-external-resources/#confidential-client) and secret values in the PMF Operations Console.
2. Deploy either of the security checks: **[UserLogin](https://pmf.persistentproducts.com/tutorials/en/foundation/9.0/application-development/authentication-and-security/user-authentication/security-check/)** or **[PinCodeAttempts](https://pmf.persistentproducts.com/tutorials/en/foundation/9.0/application-development/authentication-and-security/credentials-validation/security-check/)**.
3. Register the matching application.
4. Map the `accessRestricted` scope to the security check.
5. Update the client application to make the `WLResourceRequest` to your servlet URL.

### Supported Levels
Persistent Mobile Foundation: `9.x.x - 10.0.0`
