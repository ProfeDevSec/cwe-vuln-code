import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EncryptionService {

  async generateKey(): Promise<CryptoKey> {
    return crypto.subtle.generateKey(
      {
        name: "AES-GCM",
        length: 256
      },
      true,
      ["encrypt", "decrypt"]
    );
  }

  async encrypt(data: string, key: CryptoKey): Promise<string> {
    const encodedData = new TextEncoder().encode(data);
    const iv = crypto.getRandomValues(new Uint8Array(12)); // Initialization vector

    const encryptedData = await crypto.subtle.encrypt(
      {
        name: "AES-GCM",
        iv: iv
      },
      key,
      encodedData
    );

    const buffer = new Uint8Array(encryptedData);
    const encryptedArray = new Uint8Array(iv.length + buffer.length);
    encryptedArray.set(iv);
    encryptedArray.set(buffer, iv.length);

    return btoa(String.fromCharCode(...encryptedArray));
  }

  async decrypt(encryptedData: string, key: CryptoKey): Promise<string> {
    const encryptedArray = Uint8Array.from(atob(encryptedData), c => c.charCodeAt(0));
    const iv = encryptedArray.slice(0, 12);
    const data = encryptedArray.slice(12);

    const decryptedData = await crypto.subtle.decrypt(
      {
        name: "AES-GCM",
        iv: iv
      },
      key,
      data
    );

    return new TextDecoder().decode(decryptedData);
  }
}
