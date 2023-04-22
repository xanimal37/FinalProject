import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Complaint } from '../models/complaint';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  private url = environment.baseUrl + 'api/complaints';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }


    indexAll(): Observable<Complaint[]> {
      return this.http.get<Complaint[]>(this.url, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('ComplaintService.indexAll(): error retrieving complaints: ' + err)
          );
        })
      );
    };


    indexByComplaintId(cId: number): Observable<Complaint> {
      return this.http.get<Complaint>(this.url + "/" + cId, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('ComplaintService.indexByComplaintId(): error retrieving complaint: ' + err)
          );
        })
      );
    };


    indexComplaintsByUser(uId: number): Observable<Complaint[]> {
      return this.http.get<Complaint[]>(this.url + "/user/" + uId, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('ComplaintService.indexComplaintsByUser(): error retrieving complaints: ' + err)
          );
        })
      );
    };


    createComplaint(complaint: Complaint):Observable<Complaint> {
      return this.http.post<Complaint>(this.url, complaint, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('ComplaintService.create: error creating complaint: ' + err)
          );
        })
      );
    };


    updateComplaint(complaint: Complaint, cid: number):Observable<Complaint> {
      return this.http.put<Complaint>(this.url + "/" + cid, complaint, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('ComplaintService.updateComplaint(): error updating complaint: ' + err)
          );
        })
      );
    };

    archiveComplaint(cid: Number): Observable<void> {
      return this.http.put<void>(this.url + "/archive/" + cid, this.getHttpOptions()). pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('ComplaintService.archiveComplaint(): error archiving complaint' + err)
          );
        })
      )
    }

}
