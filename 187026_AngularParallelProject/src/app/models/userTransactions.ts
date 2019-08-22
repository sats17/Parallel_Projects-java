export class UserTransactions{
    accountId:number;
    transactionType:String;
    transactionDate:String;
    transactionAmount:number;
    toAccountId:number;
    constructor(accountId:number,transactionType:String,transactionDate:String,transactionAmount:number,
        toAccountId:number){
            this.accountId = accountId;
            this.transactionType = transactionType;
            this.transactionAmount = transactionAmount;
            this.transactionDate = transactionDate;
            this.toAccountId = toAccountId;
        }
}