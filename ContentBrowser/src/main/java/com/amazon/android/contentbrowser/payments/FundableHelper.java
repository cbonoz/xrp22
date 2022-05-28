package com.amazon.android.contentbrowser.payments;

public class FundableHelper {

    public static boolean IS_TEST = true;

    public static final String XRP_ADDRESS = "rMCcNuTcajgw7YTgBy1sys3b89QqjUrMpH";

    // https://xrpl.org/send-xrp.html

//    public static Wallet createWallet() {
//        WalletFactory walletFactory = DefaultWalletFactory.getInstance();
//        Wallet wallet = walletFactory.randomWallet(IS_TEST).wallet();
//        return wallet;
//    }
//
//    public static void fundAccount(Address classicAddress) {
//        // Fund the account using the testnet Faucet
//        FaucetClient faucetClient = FaucetClient
//                .construct(HttpUrl.get("https://faucet.altnet.rippletest.net"));
//        faucetClient.fundAccount(FundAccountRequest.of(classicAddress));
//    }
}
