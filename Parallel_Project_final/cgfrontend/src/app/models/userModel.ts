export class userModel{
    accountId:number;
    accountPassword:String;
    name:String;
    mobilenumber:String;
    balance:number;

    constructor(accountId:number,accountPassword:string,accountUsername:String,mobilenumber:String,balance:number){
        this.accountId = accountId;
        this.accountPassword = accountPassword;
        this.name = accountUsername;
        this.balance = balance;
    }

}
