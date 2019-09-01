export class userModel{
    accountId:number;
    accountPassword:String;
    accountUsername:String;
    mobilenumber:number;
    balance:number = 0;

    constructor(accountId:number,accountPassword:string,accountUsername:String,mobilenumber:number){
        this.accountId = accountId;
        this.accountPassword = accountPassword;
        this.accountUsername = accountUsername;
        //this.balance = 0;
    }

}