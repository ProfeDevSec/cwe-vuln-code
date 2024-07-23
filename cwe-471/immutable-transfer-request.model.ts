export class ImmutableTransferRequest {
    private readonly fromAccount: string;
    private readonly toAccount: string;
    private readonly amount: number;
  
    constructor(fromAccount: string, toAccount: string, amount: number) {
      this.fromAccount = fromAccount;
      this.toAccount = toAccount;
      this.amount = amount;
    }
  
    getFromAccount(): string {
      return this.fromAccount;
    }
  
    getToAccount(): string {
      return this.toAccount;
    }
  
    getAmount(): number {
      return this.amount;
    }
  }
  