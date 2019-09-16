package com.example.android1to3.php_employee;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;



@SuppressWarnings("all")
public class EmployeeRoot {
    @SerializedName("status")
    private final String status;

    @SerializedName("employees")
    private final List<Employees> employees;

    public EmployeeRoot(String status, List<Employees> employees) {
        this.status = status;
        this.employees = employees;
    }

    public String getStatus() {
        return status;
    }

    public List<Employees> getEmployees() {
        return employees;
    }

    public static class Employees implements Parcelable {
        @SerializedName("id")
        private String id;

        @SerializedName("firstname")
        private String firstname;

        @SerializedName("lastname")
        private String lastname;

        @SerializedName("mobile")
        private String mobile;

        protected Employees(Parcel in) {
            id=in.readString();
            firstname = in.readString();
            lastname = in.readString();
            mobile = in.readString();
        }

        public static final Creator<Employees> CREATOR = new Creator<Employees>() {
            @Override
            public Employees createFromParcel(Parcel in) {
                return new Employees(in);
            }

            @Override
            public Employees[] newArray(int size) {
                return new Employees[size];
            }
        };


        public Employees(String id, String firstname, String lastname, String mobile) {
            this.id = id;
            this.firstname = firstname;
            this.lastname = lastname;
            this.mobile = mobile;
        }

        public String getId() {
            return id;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public String getMobile() {
            return mobile;
        }

        @Override
        public String toString() {
            return "Employees{" +
                    "id='" + id + '\'' +
                    ", firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", mobile='" + mobile + '\'' +
                    '}';
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(id);
            parcel.writeString(firstname);
            parcel.writeString(lastname);
            parcel.writeString(mobile);
        }
    }
}
