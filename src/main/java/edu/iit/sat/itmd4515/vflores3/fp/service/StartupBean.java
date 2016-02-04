/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.service;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Address;
import edu.iit.sat.itmd4515.vflores3.fp.domain.Customer;
import edu.iit.sat.itmd4515.vflores3.fp.domain.Employees;
import edu.iit.sat.itmd4515.vflores3.fp.domain.JobType;
import edu.iit.sat.itmd4515.vflores3.fp.domain.Project;
import edu.iit.sat.itmd4515.vflores3.fp.domain.Skill;
import edu.iit.sat.itmd4515.vflores3.fp.security.Group;
import edu.iit.sat.itmd4515.vflores3.fp.security.User;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Vanessa
 */
@Singleton
@Startup
public class StartupBean {

    @EJB
    GroupService groupService;
    @EJB
    UserService userService;
    @EJB
    JobTypeService jobTypeService;
    @EJB
    SkillService skillService;
    @EJB
    CustomerService customerService;
    @EJB
    AddressService addressService;
    @EJB
    EmployeesService employeesService;
    @EJB
    ProjectService projectService;

    /**
     * Constructor
     */
    public StartupBean() {
    }

    @PostConstruct
    private void doWork() {

        // Create groups to hold users
        Group clients = new Group("clients", "Users that are clients and not employees of the company this program is for.");
        Group managers = new Group("managers", "Users that have management authorization.");
        Group teamLeaders = new Group("teamLeaders", "Users that have team leader authorization for project.");
        Group teamMembers = new Group("teamMembers", "Users that have team member authorization for project.");
        Group administrators = new Group("administrators", "Users that are system administrators only");
        groupService.create(clients);
        groupService.create(managers);
        groupService.create(teamLeaders);
        groupService.create(teamMembers);
        groupService.create(administrators);

        // Create users to put into groups
        User client1 = new User("client1", "c1Password");
        User client2 = new User("client2", "c2Password");
        User client3 = new User("client3", "c3Password");
        User client4 = new User("client4", "c4Password");
        User manager1 = new User("manager1", "m1Password");
        User manager2 = new User("manager2", "m2Password");
        User teamLeader1 = new User("teamLeader1", "tL1Password");
        User teamLeader2 = new User("teamLeader2", "tL2Password");
        User teamMember1 = new User("teamMember1", "tM1Password");
        User teamMember2 = new User("teamMember2", "tM2Password");
        User admin = new User("admin", "admin");
        // Add users to their groups
        client1.addUserToGroup(clients);
        client2.addUserToGroup(clients);
        client3.addUserToGroup(clients);
        client4.addUserToGroup(clients);
        manager1.addUserToGroup(managers);
        manager2.addUserToGroup(managers);
        teamLeader1.addUserToGroup(teamLeaders);
        teamLeader2.addUserToGroup(teamLeaders);
        teamMember1.addUserToGroup(teamMembers);
        teamMember2.addUserToGroup(teamMembers);
        admin.addUserToGroup(administrators);
        userService.create(client1);
        userService.create(client2);
        userService.create(client3);
        userService.create(client4);
        userService.create(manager1);
        userService.create(manager2);
        userService.create(teamLeader1);
        userService.create(teamLeader2);
        userService.create(teamMember1);
        userService.create(teamMember2);
        userService.create(admin);

        // Create job types
        JobType jobType1 = new JobType("Software Developer");
        JobType jobType2 = new JobType("Web Developer");
        JobType jobType3 = new JobType("Database Developer");
        JobType jobType4 = new JobType("Graphic Designer ");
        JobType jobType5 = new JobType("Project Manager");
        JobType jobType6 = new JobType("Software Tester");
        JobType jobType7 = new JobType("Help Desk Analyst");
        JobType jobType8 = new JobType("Network Administrator");
        JobType jobType9 = new JobType("Manager");
        jobTypeService.create(jobType1);
        jobTypeService.create(jobType2);
        jobTypeService.create(jobType3);
        jobTypeService.create(jobType4);
        jobTypeService.create(jobType5);
        jobTypeService.create(jobType6);
        jobTypeService.create(jobType7);
        jobTypeService.create(jobType8);
        jobTypeService.create(jobType9);

        // Create skills
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("C++");
        Skill skill3 = new Skill("iOS");
        Skill skill4 = new Skill("Swift");
        Skill skill5 = new Skill("Mac");
        Skill skill6 = new Skill("JEE");
        Skill skill7 = new Skill("Linux");
        Skill skill8 = new Skill("Windows");
        Skill skill9 = new Skill("Android");
        Skill skill10 = new Skill("MySQL");
        Skill skill11 = new Skill("Security");
        Skill skill12 = new Skill("Networking");
        Skill skill13 = new Skill("Management");
        Skill skill14 = new Skill("Project Management");
        Skill skill15 = new Skill("HTML");
        Skill skill16 = new Skill("CSS");
        Skill skill17 = new Skill("JavaScript");
        skillService.create(skill1);
        skillService.create(skill2);
        skillService.create(skill3);
        skillService.create(skill4);
        skillService.create(skill5);
        skillService.create(skill6);
        skillService.create(skill7);
        skillService.create(skill8);
        skillService.create(skill9);
        skillService.create(skill10);
        skillService.create(skill11);
        skillService.create(skill12);
        skillService.create(skill13);
        skillService.create(skill14);
        skillService.create(skill15);
        skillService.create(skill16);
        skillService.create(skill17);

        // Create customers that will be considered clients
        Customer customer1 = new Customer("Catholic Cemeteries of Chicago", "William Burbatt", "708-441-6100", new GregorianCalendar(2001, 1, 3).getTime());
        Customer customer2 = new Customer("McDonald's Corporation", "Paul Hackiewicz", "773-252-6120", new GregorianCalendar(2003, 3, 5).getTime());
        Customer customer3 = new Customer("Chickie's Restaurant", "Charles Dickens", "312-555-0254", new GregorianCalendar(2005, 5, 7).getTime());
        Customer customer4 = new Customer("La Michoacana", "Maria Martinez", "708-222-0120", new GregorianCalendar(2007, 7, 9).getTime());
        // Link the customer with the user usernames
        customer1.setUser(client1);
        customer2.setUser(client2);
        customer3.setUser(client3);
        customer4.setUser(client4);
        customerService.create(customer1);
        customerService.create(customer2);
        customerService.create(customer3);
        customerService.create(customer4);

        // Create addresses that will belong to customers
        Address address1 = new Address("2901 Butterfield Rd", "Suite 201", "Oakbrook", "IL", "60523");
        Address address2 = new Address("809 E Oak Park Ave", "Apt 2A", "Oak Park", "IL", "60608");
        Address address3 = new Address("1051 Perimeter Dr", "#1125", "Schaumburg", "IL", "60159");
        Address address4 = new Address("1633 Highridge Pwky", "", "Westchester", "IL", "60136");
        // Add the addresses to the customers
        /*customer1.addToAddressesList(address1);
        customer2.addToAddressesList(address2);
        customer3.addToAddressesList(address3);
        customer4.addToAddressesList(address4);*/
        customer1.setAddress(address1);
        customer2.setAddress(address2);
        customer3.setAddress(address3);
        customer4.setAddress(address4);
        addressService.create(address1);
        addressService.create(address2);
        addressService.create(address3);
        addressService.create(address4);

        // Create employees
        Employees employee1 = new Employees("Owen", "Wilson", "256-555-9654", "632547851", 86500.00, new GregorianCalendar(1980, 2, 5).getTime(), new GregorianCalendar(2008, 5, 3).getTime(), null);
        Employees employee2 = new Employees("Sarah", "Marshall", "773-654-8521", "365471287", 88700.00, new GregorianCalendar(1985, 7, 18).getTime(), new GregorianCalendar(2010, 6, 19).getTime(), null);
        Employees employee3 = new Employees("Will", "Smith", "331-200-6100", "632145879", 71300.00, new GregorianCalendar(1989, 1, 6).getTime(), new GregorianCalendar(2014, 9, 2).getTime(), null);
        Employees employee4 = new Employees("Ted", "Mosby", "222-555-3210", "263541823", 65700.00, new GregorianCalendar(1979, 11, 13).getTime(), new GregorianCalendar(2002, 8, 21).getTime(), null);
        Employees employee5 = new Employees("Larry", "Stooge", "708-236-1254", "362548512", 79800.00, new GregorianCalendar(1975, 7, 17).getTime(), new GregorianCalendar(2006, 4, 19).getTime(), null);
        Employees employee6 = new Employees("Richard", "Drozdowski", "630-124-9632", "342569871", 48600.00, new GregorianCalendar(1985, 6, 22).getTime(), new GregorianCalendar(2007, 4, 19).getTime(), null);
        // Link employees with user usernames
        employee1.setUser(manager1);
        employee2.setUser(manager2);
        employee3.setUser(teamLeader1);
        employee4.setUser(teamLeader2);
        employee5.setUser(teamMember1);
        employee6.setUser(teamMember2);
        employeesService.create(employee1);
        employeesService.create(employee2);
        employeesService.create(employee3);
        employeesService.create(employee4);
        employeesService.create(employee5);
        employeesService.create(employee6);

        // Create addresses that will belong to employees
        Address address5 = new Address("1925 S Allport St", "Unit 2", "Chicago", "IL", "60808");
        Address address6 = new Address("2S741 Ave Chateaux E", "", "Oak Brook", "IL", "60523");
        Address address7 = new Address("306 E 3rd St", "", "Hinsdale", "IL", "60521");
        Address address8 = new Address("5124 Grand Ave", "", "Western Springs", "IL", "60558");
        Address address9 = new Address("1448 Park Ave", "", "River Forest", "IL", "60305");
        Address address10 = new Address("1S116 Kirkland Ln", "", "Villa Park", "IL", "60181");
        // Add the addresses to the employees
        /*employee1.addToAddressesList(address5);
        employee2.addToAddressesList(address6);
        employee3.addToAddressesList(address7);
        employee4.addToAddressesList(address8);
        employee5.addToAddressesList(address9);
        employee6.addToAddressesList(address10);*/
        employee1.setAddress(address5);
        employee2.setAddress(address6);
        employee3.setAddress(address7);
        employee4.setAddress(address8);
        employee5.setAddress(address9);
        employee6.setAddress(address10);
        addressService.create(address5);
        addressService.create(address6);
        addressService.create(address7);
        addressService.create(address8);
        addressService.create(address9);
        addressService.create(address10);

        // Add job type to employees
        employee1.setJobType(jobType9);
        employee2.setJobType(jobType9);
        employee3.setJobType(jobType5);
        employee4.setJobType(jobType5);
        employee5.setJobType(jobType1);
        employee6.setJobType(jobType3);

        // Add skills to employees
        employee1.addSkill(skill13);
        employee1.addSkill(skill1);
        employee1.addSkill(skill6);
        employee2.addSkill(skill13);
        employee2.addSkill(skill5);
        employee2.addSkill(skill7);
        employee2.addSkill(skill8);
        employee3.addSkill(skill14);
        employee3.addSkill(skill11);
        employee3.addSkill(skill12);
        employee3.addSkill(skill8);
        employee4.addSkill(skill14);
        employee4.addSkill(skill6);
        employee4.addSkill(skill1);
        employee4.addSkill(skill2);
        employee4.addSkill(skill3);
        employee5.addSkill(skill1);
        employee5.addSkill(skill2);
        employee5.addSkill(skill3);
        employee5.addSkill(skill4);
        employee5.addSkill(skill9);
        employee6.addSkill(skill1);
        employee6.addSkill(skill15);
        employee6.addSkill(skill16);
        employee6.addSkill(skill17);

        // Create projects
        Project project1 = new Project("CCC-WB-1001", "Reconfigure Website", new Date(), null, "Create website to not use Wordpress anymore.");
        Project project2 = new Project("McDonC-PH-1001", "Native App", new GregorianCalendar(2013, 4, 14).getTime(), new GregorianCalendar(2013, 6, 15).getTime(), "Employee progress tracker.");
        Project project3 = new Project("LMich-MM-1001", "Website Development", new GregorianCalendar(2011, 3, 23).getTime(), null, "Design and develop website");
        Project project4 = new Project("CCC-WB-1002", "Blog Update", new GregorianCalendar(2008, 1, 12).getTime(), new GregorianCalendar(2008, 1, 25).getTime(), "Update blog design.");
        Project project5 = new Project("Chickies-CD-1001", "Mobile App", new GregorianCalendar(2015, 9, 19).getTime(), null, "Create mobile app with order placement capabilities.");
        // Set the customer of the project
        project1.addCustomerToProject(customer1);
        project2.addCustomerToProject(customer2);
        project3.addCustomerToProject(customer4);
        project4.addCustomerToProject(customer1);
        project5.addCustomerToProject(customer3);
        // Add project to list of projects employee is on
        employee1.addProject(project1);
        employee1.addProject(project2);
        employee2.addProject(project3);
        employee2.addProject(project4);
        employee2.addProject(project5);
        employee3.addProject(project2);
        employee3.addProject(project5);
        employee4.addProject(project1);
        employee4.addProject(project3);
        employee4.addProject(project4);
        employee5.addProject(project2);
        employee5.addProject(project5);
        employee6.addProject(project1);
        employee6.addProject(project3);
        employee6.addProject(project4);
        // Add employees to the list of employees on a project
        project1.addEmployeesToProject(employee1);
        project1.addEmployeesToProject(employee4);
        project1.addEmployeesToProject(employee6);
        project2.addEmployeesToProject(employee1);
        project2.addEmployeesToProject(employee3);
        project2.addEmployeesToProject(employee5);
        project3.addEmployeesToProject(employee2);
        project3.addEmployeesToProject(employee4);
        project3.addEmployeesToProject(employee6);
        project4.addEmployeesToProject(employee2);
        project4.addEmployeesToProject(employee4);
        project4.addEmployeesToProject(employee6);
        project5.addEmployeesToProject(employee2);
        project5.addEmployeesToProject(employee3);
        project5.addEmployeesToProject(employee5);
        // Create the projects in projectService
        projectService.create(project1);
        projectService.create(project2);
        projectService.create(project3);
        projectService.create(project4);
        projectService.create(project5);

    }

}
