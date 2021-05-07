import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export class GenericService<T, ID> {
  constructor(protected http: HttpClient, protected url: string) { }

  //permis
  //login, createUser
  login(email: string, password: string): Observable<T> {
    return this.http.get<T>(this.url + '/' + email + '/' + password);
  }
  //permis
  //createChild, createUser
  save(t: T): Observable<T> {
    return this.http.post<T>(this.url + '/' + t, t);
  }
  //ministere
  //createChild, createUser
  checkCitoyenValidity(nassm: string, prenom: string, nom: string, age: number, ville: string): Observable<boolean> {
    return this.http.get<boolean>(this.url + '/' + nassm + '/' + prenom + '/' + nom + '/' + age + '/' + ville);
  }

  //permis
  //dashboard
  findEnfantsByTuteurEmail(email: string): Observable<T[]> {
    return this.http.get<T[]>(this.url + '/' + email);
  }

  //ministere
  //requestPermis
  checkCitizenPermisValidity(nassm: string): Observable<string> {
    return this.http.get(this.url + '/' + nassm, { responseType: 'text' });
  }
  //permis
  //requestPermis
  createPermis(id: ID, typePermis: string): Observable<T> {
    console.log("yes");
    return this.http.put<T>(this.url + '/' + id + '&' + typePermis, {});
  }
  //ministere
  //renewPermis
  checkCitizenValidityForRenewing(email: string, numTelephone: string, ville: string): Observable<boolean> {
    return this.http.get<boolean>(this.url + '/' + email + '&' + numTelephone + '&' + ville);
  }
  //permis
  //renewPermis
  renewPermis(id: ID): Observable<T> {
    return this.http.put<T>(this.url + '/renew/' + id, {});
  }

  //permis
  //getPermis
  sendEmail(id: ID, email: string): Observable<boolean> {
    return this.http.get<boolean>(this.url + '&' + id + '&' + email);
  }

  //permis
  //getPermis
  getPDF(id: ID): Observable<File> {
    return this.http.get<File>(this.url + '&' + id);
  }



  //Not use
  findIdByEmail(email: string): Observable<ID> {
    return this.http.get<ID>(this.url + '/' + email);
  }
  update(id: ID, t: T): Observable<T> {
    return this.http.put<T>(this.url + '/' + id, t, {});
  }
  deleteById(id: ID): Observable<T> {
    return this.http.delete<T>(this.url + '/' + id);
  }
  findAll(): Observable<T[]> {
    return this.http.get<T[]>(this.url);
  }
  findById(id: ID): Observable<T> {
    return this.http.get<T>(this.url + '/' + id);
  }
}
