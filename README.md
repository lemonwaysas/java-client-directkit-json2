⚠️WARNING: This client is deprecated⚠️ 

The LemonWay API (called Directkit) has two implementations: Directkit**Json2** and Directkit**Xml**. 

The Directkit**Json2** is recommended over the Directkit**Xml** because It is the simplest and the most network-efficient way.


This tutorial show how simple it is.

# Sample codes

```java
LemonWayService service = new LemonWayService("society", "123456");
Map<String, String> param = new HashMap<String, String>() {{
    put("wallet", "9999");
}};
JSONObject response = service.callService("GetWalletDetails", param);
```
See also: [LemonWay API documentation](http://documentation.lemonway.fr/) / method [`GetWalletDetails`](http://documentation.lemonway.fr/api-en/directkit/manage-wallets/getwalletdetails-getting-detailed-wallet-data)

# How to run

After downloading this project (`git clone`), run:
```
gradlew run 
```
Out of the box it will call the `demo` environment. If you have your own test environment. You should fix the configuration in `LemonWayService.java`, put your own environment configuration.

# Time to play!

The example is only the basic, you can also play with our API by calling other services. For example:
- [Create a new wallet](http://documentation.lemonway.fr/api-en/directkit/manage-wallets/registerwallet-creating-a-new-wallet)
- [Create a payment link to credit a wallet](http://documentation.lemonway.fr/api-en/directkit/money-in-credit-a-wallet/by-card/moneyinwebinit-indirect-mode-money-in-by-card-crediting-a-wallet)
- [Credit the wallet without 3D secure](http://documentation.lemonway.fr/api-en/directkit/money-in-credit-a-wallet/by-card/moneyin-credit-a-wallet-with-a-non-3d-secure-card-payment)
- [Credit the wallet with 3D secure](http://documentation.lemonway.fr/api-en/directkit/money-in-credit-a-wallet/by-card/moneyin3dinit-direct-mode-3d-secure-payment-init-to-credit-a-wallet)
- [Create a payment form to credit a wallet](http://documentation.lemonway.fr/api-en/directkit/money-in-credit-a-wallet/payment-form)
- [Register a Credit card to the wallet](http://documentation.lemonway.fr/api-en/directkit/money-in-credit-a-wallet/by-card/registercard-linking-a-card-number-to-a-wallet-for-one-click-payment-or-rebill)
- [Register an IBAN to the wallet](http://documentation.lemonway.fr/api-en/directkit/money-out-debit-a-wallet-and-credit-a-bank-account/registeriban-link-an-iban-to-a-wallet)
- [Transfer money from wallet to a bank account](http://documentation.lemonway.fr/api-en/directkit/money-out-debit-a-wallet-and-credit-a-bank-account/moneyout-external-fund-transfer-from-a-wallet-to-a-bank-account)
- [Transfer money from wallet to other wallet](http://documentation.lemonway.fr/api-en/directkit/p2p-transfer-between-wallets/sendpayment-on-us-payment-between-wallets)

