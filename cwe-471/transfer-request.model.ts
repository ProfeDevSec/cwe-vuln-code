export class TransferRequest {
    constructor(
      public fromAccount: string,
      public toAccount: string,
      public amount: number
    ) {}
  }
  