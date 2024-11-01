package com.example.backend;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.backend.data.entity.ActivityEntity;
import com.example.backend.data.entity.CategoryEntity;
import com.example.backend.data.entity.ClientEntity;
import com.example.backend.data.entity.CountryEntity;
import com.example.backend.data.entity.ProjectEntity;
import com.example.backend.data.entity.UserEntity;
import com.example.backend.data.enums.Role;
import com.example.backend.data.enums.UserStatus;
import com.example.backend.data.repository.IActivityJPARepository;
import com.example.backend.data.repository.ICategoryJPARepository;
import com.example.backend.data.repository.IClientJPARepository;
import com.example.backend.data.repository.ICountryJPARepository;
import com.example.backend.data.repository.IProjectJPARepository;
import com.example.backend.data.repository.IUserJPARepository;

import lombok.RequiredArgsConstructor;;

@RequiredArgsConstructor
@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
	private final IUserJPARepository userJPARepository;
	private final ICountryJPARepository countryJPARepository;
	private final IClientJPARepository clientJPARepository;
	private final IProjectJPARepository projectJPARepository;
	private final ICategoryJPARepository categoryJPARepository;
	private final IActivityJPARepository activityJPARepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	// Helper function to get the last workday (skipping weekends)
	private LocalDate getWorkdayAgo(int daysAgo) {
		LocalDate date = LocalDate.now();
		while (daysAgo > 0) {
			date = date.minusDays(1);
			if (!(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
				daysAgo--;
			}
		}
		return date;
	}

	@Override
	public void run(String... args) throws Exception {

		// Create and save users

		UserEntity userEntity1 = new UserEntity("Alice Johnson", "@alice.johnson", "Pa$$w0rd!",
				"alice.johnson@example.com",
				8.0, Role.ADMIN, UserStatus.ACTIVE);
		userJPARepository.save(userEntity1);

		UserEntity userEntity2 = new UserEntity("Bob Smith", "@bob.smith", "Pass123!", "bob.smith@example.com", 8.0,
				Role.ADMIN, UserStatus.ACTIVE);
		userJPARepository.save(userEntity2);

		UserEntity userEntity3 = new UserEntity("Charlie Brown", "@charlie.brown", "Charlie12!",
				"charlie.brown@example.com", 8.0, Role.WORKER, UserStatus.ACTIVE);
		userJPARepository.save(userEntity3);

		UserEntity userEntity4 = new UserEntity("David Lee", "@david.lee", "David34!", "david.lee@example.com", 8.0,
				Role.WORKER, UserStatus.ACTIVE);
		userJPARepository.save(userEntity4);

		UserEntity userEntity5 = new UserEntity("Emma Wilson", "@emma.wilson", "Emma5678!", "emma.wilson@example.com",
				8.0,
				Role.WORKER, UserStatus.ACTIVE);
		userJPARepository.save(userEntity5);

		UserEntity userEntity6 = new UserEntity("Fiona Davis", "@fiona.davis", "Fiona123!", "fiona.davis@example.com",
				4.0,
				Role.WORKER, UserStatus.ACTIVE);
		userJPARepository.save(userEntity6);

		UserEntity userEntity7 = new UserEntity("George Miller", "@george.miller", "George456!",
				"george.miller@example.com", 8.0, Role.WORKER, UserStatus.ACTIVE);
		userJPARepository.save(userEntity7);

		UserEntity userEntity8 = new UserEntity("Hannah Taylor", "@hannah.taylor", "Hannah789!",
				"hannah.taylor@example.com", 8.0, Role.WORKER, UserStatus.ACTIVE);
		userJPARepository.save(userEntity8);

		UserEntity userEntity9 = new UserEntity("Ian Parker", "@ian.parker", "Ian1234!", "ian.parker@example.com", 8.0,
				Role.WORKER, UserStatus.ACTIVE);
		userJPARepository.save(userEntity9);

		UserEntity userEntity10 = new UserEntity("Julia Evans", "@julia.evans", "Julia5678!", "julia.evans@example.com",
				8.0, Role.WORKER, UserStatus.ACTIVE);
		userJPARepository.save(userEntity10);

		// Create and save countries
		CountryEntity countryEntity1 = new CountryEntity("United States");
		CountryEntity countryEntity2 = new CountryEntity("Canada");
		CountryEntity countryEntity3 = new CountryEntity("United Kingdom");
		CountryEntity countryEntity4 = new CountryEntity("Germany");
		CountryEntity countryEntity5 = new CountryEntity("France");
		CountryEntity countryEntity6 = new CountryEntity("Australia");
		CountryEntity countryEntity7 = new CountryEntity("Japan");
		CountryEntity countryEntity8 = new CountryEntity("Brazil");
		CountryEntity countryEntity9 = new CountryEntity("India");
		CountryEntity countryEntity10 = new CountryEntity("South Africa");

		countryJPARepository.save(countryEntity1);
		countryJPARepository.save(countryEntity2);
		countryJPARepository.save(countryEntity3);
		countryJPARepository.save(countryEntity4);
		countryJPARepository.save(countryEntity5);
		countryJPARepository.save(countryEntity6);
		countryJPARepository.save(countryEntity7);
		countryJPARepository.save(countryEntity8);
		countryJPARepository.save(countryEntity9);
		countryJPARepository.save(countryEntity10);

		// Create and save categories
CategoryEntity categoryEntity1 = new CategoryEntity("Frontend");
CategoryEntity categoryEntity2 = new CategoryEntity("Backend");
CategoryEntity categoryEntity3 = new CategoryEntity("Database");
CategoryEntity categoryEntity4 = new CategoryEntity("DevOps");
CategoryEntity categoryEntity5 = new CategoryEntity("Cloud Computing");
CategoryEntity categoryEntity6 = new CategoryEntity("Testing & QA");
CategoryEntity categoryEntity7 = new CategoryEntity("Security");
CategoryEntity categoryEntity8 = new CategoryEntity("UI/UX Design");
CategoryEntity categoryEntity9 = new CategoryEntity("Data Science & Analytics");
CategoryEntity categoryEntity10 = new CategoryEntity("Mobile Development");


		categoryJPARepository.save(categoryEntity1);
		categoryJPARepository.save(categoryEntity2);
		categoryJPARepository.save(categoryEntity3);
		categoryJPARepository.save(categoryEntity4);
		categoryJPARepository.save(categoryEntity5);
		categoryJPARepository.save(categoryEntity6);
		categoryJPARepository.save(categoryEntity7);
		categoryJPARepository.save(categoryEntity8);
		categoryJPARepository.save(categoryEntity9);
		categoryJPARepository.save(categoryEntity10);

		// Create and save clients
ClientEntity clientEntity1 = new ClientEntity("Tech Innovations Inc.", "123 Silicon Valley", "San Jose", "95131", countryEntity1);
ClientEntity clientEntity2 = new ClientEntity("DataHub Solutions", "456 Queen St.", "Toronto", "M5H 2N2", countryEntity2);
ClientEntity clientEntity3 = new ClientEntity("Quantum Widgets Ltd.", "789 Westminster", "London", "SW1A 1AA", countryEntity3);
ClientEntity clientEntity4 = new ClientEntity("Deutsche TechWorks", "101 Hauptstraße", "Berlin", "10115", countryEntity4);
ClientEntity clientEntity5 = new ClientEntity("PariSoft Technologies", "202 Rue de Rivoli", "Paris", "75001", countryEntity5);
ClientEntity clientEntity6 = new ClientEntity("Aussie Code Labs", "303 Sydney Harbour", "Sydney", "2000", countryEntity6);
ClientEntity clientEntity7 = new ClientEntity("Sakura Tech Solutions", "404 Cherry Blossom Ave.", "Tokyo", "100-0001", countryEntity7);
ClientEntity clientEntity8 = new ClientEntity("Titan Solutions", "505 Avenida Brasil", "Rio de Janeiro", "22250-000", countryEntity8);
ClientEntity clientEntity9 = new ClientEntity("Thunder Solutions", "606 MG Road", "Bangalore", "560001", countryEntity9);
ClientEntity clientEntity10 = new ClientEntity("Cape Cloud Innovations", "707 Cape Town Road", "Cape Town", "8001", countryEntity10);


		clientJPARepository.save(clientEntity1);
		clientJPARepository.save(clientEntity2);
		clientJPARepository.save(clientEntity3);
		clientJPARepository.save(clientEntity4);
		clientJPARepository.save(clientEntity5);
		clientJPARepository.save(clientEntity6);
		clientJPARepository.save(clientEntity7);
		clientJPARepository.save(clientEntity8);
		clientJPARepository.save(clientEntity9);
		clientJPARepository.save(clientEntity10);

		// Create and save projects
ProjectEntity projectEntity1 = new ProjectEntity("Corporate Website Revamp",
        "Redesigning and upgrading the corporate website for enhanced user experience.", clientEntity1, userEntity1);
ProjectEntity projectEntity2 = new ProjectEntity("Automated Financial Audit Software",
        "Building software for automating financial audits and report generation.", clientEntity2, userEntity1);
ProjectEntity projectEntity3 = new ProjectEntity("Patient Management App",
        "Developing a mobile application for managing patient records and appointments.", clientEntity3, userEntity3);
ProjectEntity projectEntity4 = new ProjectEntity("Online Learning Platform",
        "Creating an interactive platform for online courses and student engagement.", clientEntity4, userEntity4);
ProjectEntity projectEntity5 = new ProjectEntity("Retail Sales Analytics Tool",
        "Developing software for real-time sales analytics and optimization.", clientEntity5, userEntity5);
ProjectEntity projectEntity6 = new ProjectEntity("Comprehensive Hotel Management System",
        "Creating software to streamline hotel operations, bookings, and services.", clientEntity6, userEntity6);
ProjectEntity projectEntity7 = new ProjectEntity("Logistics Optimization Software",
        "Designing software to enhance logistics and supply chain management.", clientEntity7, userEntity7);
ProjectEntity projectEntity8 = new ProjectEntity("Real Estate Management System",
        "Building a platform for managing real estate transactions and property listings.", clientEntity8, userEntity8);
ProjectEntity projectEntity9 = new ProjectEntity("Social Media Campaign Manager",
        "Developing a software tool for managing social media marketing campaigns.", clientEntity9, userEntity9);
ProjectEntity projectEntity10 = new ProjectEntity("Promotional Video Streaming Platform",
        "Creating a platform for hosting and streaming promotional and marketing videos.", clientEntity10, userEntity10);

		projectJPARepository.save(projectEntity1);
		projectJPARepository.save(projectEntity2);
		projectJPARepository.save(projectEntity3);
		projectJPARepository.save(projectEntity4);
		projectJPARepository.save(projectEntity5);
		projectJPARepository.save(projectEntity6);
		projectJPARepository.save(projectEntity7);
		projectJPARepository.save(projectEntity8);
		projectJPARepository.save(projectEntity9);
		projectJPARepository.save(projectEntity10);

		// Create and save activities for Tech Innovations Inc.
ActivityEntity activityEntity1 = new ActivityEntity(LocalDate.now(), "Website Wireframing", 3.5, 0.0,
        clientEntity1, projectEntity1, categoryEntity1, userEntity1);
ActivityEntity activityEntity2 = new ActivityEntity(LocalDate.now(), "UI/UX Design", 4.5, 0.0,
        clientEntity1, projectEntity1, categoryEntity1, userEntity1);

ActivityEntity activityEntity3 = new ActivityEntity(getWorkdayAgo(1), "Backend Development", 4.0, 0.0,
        clientEntity1, projectEntity1, categoryEntity1, userEntity1);
ActivityEntity activityEntity4 = new ActivityEntity(getWorkdayAgo(1), "Frontend Implementation", 3.0, 0.0,
        clientEntity1, projectEntity1, categoryEntity1, userEntity1);

ActivityEntity activityEntity5 = new ActivityEntity(getWorkdayAgo(1), "Testing & Debugging", 1.0, 0.0,
        clientEntity1, projectEntity1, categoryEntity1, userEntity1);

// Create and save activities for DataHub Solutions
ActivityEntity activityEntity6 = new ActivityEntity(getWorkdayAgo(2), "Audit Automation", 8.0, 0.0,
        clientEntity2, projectEntity2, categoryEntity2, userEntity1);

ActivityEntity activityEntity7 = new ActivityEntity(getWorkdayAgo(3), "Tax Reporting Module Development", 8.0, 2.0,
        clientEntity2, projectEntity2, categoryEntity2, userEntity1);

ActivityEntity activityEntity8 = new ActivityEntity(getWorkdayAgo(4), "Financial Statements Generator Development", 8.0,
        0.0, clientEntity2, projectEntity2, categoryEntity2, userEntity1);

// Create and save activities for Quantum Widgets Ltd.
ActivityEntity activityEntity9 = new ActivityEntity(getWorkdayAgo(5), "Patient Record Management Feature", 6.0, 1.0,
        clientEntity3, projectEntity3, categoryEntity3, userEntity3);
ActivityEntity activityEntity10 = new ActivityEntity(getWorkdayAgo(6), "Appointment Scheduling Feature", 5.0, 0.0,
        clientEntity3, projectEntity3, categoryEntity3, userEntity3);

		activityJPARepository.save(activityEntity1);
		activityJPARepository.save(activityEntity2);
		activityJPARepository.save(activityEntity3);
		activityJPARepository.save(activityEntity4);
		activityJPARepository.save(activityEntity5);
		activityJPARepository.save(activityEntity6);
		activityJPARepository.save(activityEntity7);
		activityJPARepository.save(activityEntity8);
	activityJPARepository.save(activityEntity9);
		activityJPARepository.save(activityEntity10);

	}

}
