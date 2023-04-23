import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Complaint } from 'src/app/models/complaint';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ComplaintService } from 'src/app/services/complaint.service';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {

  title: string = "Complaints";
  complaint: Complaint | null = null;
  selected: Complaint | null =  null;
  selectedComplaint: Complaint[] | null = null;
  newComplaint: Complaint = new Complaint;
  editComplaint: Complaint | null = null;
  complaints: Complaint[] = []
  showEnabled: boolean = false;
  loggedInUser: User | undefined;
  admin: string = "admin";
  active: string = "active";
  closed: string = "closed";


  constructor(
    private complaintService: ComplaintService,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    ) {
    }

  displayComplaint(complaint: Complaint) {
    this.selected = complaint;
  }

  displayTable():void {
    this.selected = null;
  }

  setEditComplaint(complaint: Complaint): void {
    this.editComplaint = complaint;
  }
  cancelEditComplaint(): void {
    this.editComplaint = null;
  }

  ngOnInit(): void {
    this.authService.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.loggedInUser = user;
      },
      error: (nojoy) => {
        console.log(nojoy);
      }

     });
    this.reload();
   }

   reload() {
    this.complaintService.indexAll().subscribe({
      next: (complaintList) => {
        this.complaints = complaintList;
      },
      error: (err) => {
        console.error('Error getting complaints list');
        console.error(err);
      }
    });
  }

  getComplaintsByUser(uId: number) {
    this.complaintService.indexComplaintsByUser(uId).subscribe({
      next: (complaintUserList) => {
        this.complaints = complaintUserList;
      },
      error: (err) => {
        console.error('Error getting complaints list');
        console.error(err);
      }
    });
  }

  getSingleComplaint(cId: number) {
    this.complaintService.indexByComplaintId(cId).subscribe({
      next: (singleComplaint) => {
        this.complaint = singleComplaint;
      },
      error: (err) => {
        console.error('Error getting complaints list');
        console.error(err);
      }
    });
  }

  createComplaint(complaint: Complaint) {
    this.complaintService.createComplaint(complaint).subscribe( {
      next: (createdComplaint) => {
        this.newComplaint = createdComplaint;
        this.reload();
      },
      error: (fail) => {
        console.error('Error creating complaint');
        console.error(fail);
      }
    });

    this.reload();
  }

  updateComplaint(complaint: Complaint, cId: number) {
    this.complaintService.updateComplaint(complaint,cId).subscribe( {
      next: (updateComplaint) => {
        this.editComplaint = updateComplaint;
        this.reload();
      },
      error: (fail) => {
        console.error('Error editing complaint');
        console.error(fail);
      }
    });
    this.reload();
  }


}
