Persistent Mobile Foundation
===
## JavaTokenValidator
A sample application demonstrating use of the Java Token Validator filter.

### Tutorials
https://pmf.persistentproducts.com/tutorials/en/foundation/10x/application-development/authentication-and-security/protecting-external-resources/

### Usage

This sample can be used in coordination with client samples applications described here:

- [UserLogin](https://pmf.persistentproducts.com/tutorials/en/foundation/10x/application-development/authentication-and-security/user-authentication/security-check/)
- [PinCodeAttempts](https://pmf.persistentproducts.com/tutorials/en/foundation/10x/application-development/authentication-and-security/credentials-validation/security-check/)

#### Build
This project requires Maven and Java 21.
Run below command to build the project.

```sh
mvn clean install
```
#### Deployment
You can deploy the project on supported application servers (Open Liberty and WebSphere Liberty profile).

Sample server.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<server description="new server">

    <!-- Enable features -->
    <featureManager>
		<feature>xmlBinding-4.0</feature>
        <feature>restfulWSClient-3.1</feature>
        <feature>servlet-6.0</feature>
    </featureManager>

    <!-- To access this server from a remote client add a host attribute to the following element, e.g. host="*" -->
    <httpEndpoint id="defaultHttpEndpoint"
                  httpPort="9081"
                  httpsPort="9444" host="*"/>
                  
    <!-- Automatically expand WAR files and EAR files -->
    <applicationManager autoExpand="true"/>
	
	<application id="jtvapp" name="jtvapp" location="JavaTokenValidator-1.0-SNAPSHOT.war" type="war">
        <classloader delegation="parentLast">
            </classloader>
    </application>

</server>
```

### Sample usage

1. Make sure to [update the confidential client](https://pmf.persistentproducts.com/tutorials/en/foundation/10x/application-development/authentication-and-security/protecting-external-resources/#confidential-client) and secret values in the PMF Operations Console.
2. Deploy either of the security checks: **[UserLogin](https://pmf.persistentproducts.com/tutorials/en/foundation/10x/application-development/authentication-and-security/user-authentication/security-check/)** or **[PinCodeAttempts](https://pmf.persistentproducts.com/tutorials/en/foundation/10x/application-development/authentication-and-security/credentials-validation/security-check/)**.
3. Register the matching application.
4. Map the `accessRestricted` scope to the security check.
5. Update the client application to make the `WLResourceRequest` to your servlet URL.

### Supported Levels
Persistent Mobile Foundation: `10.1.0 and later`

Note

Please switch to release-9x branch for older release.
