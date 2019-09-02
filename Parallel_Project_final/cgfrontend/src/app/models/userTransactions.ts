export class UserTransactions{
    transactionId:number;
    transactionType:String;
    transactionDate:String;
    amount:number;
    toAccountId:number;
    constructor(transactionId:number,transactionType:String,transactionDate:String,amount:number,
        toAccountId:number){
            this.transactionId = transactionId;
            this.transactionType = transactionType;
            this.amount = amount;
            this.transactionDate = transactionDate;
            this.toAccountId = toAccountId;
        }
}

// 
