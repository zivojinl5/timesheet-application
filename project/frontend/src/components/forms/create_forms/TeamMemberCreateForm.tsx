import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { createTeamMember } from "../../../service/TeamMemberService";
import { z, ZodType } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
type UserData = {
  name: string;
  username: string;
  password: string;
  confirmPassword: string;
  email: string;
  hours: number;
  userStatus: string;
  role: string;
};
export default function TeamMemberCreateForm({ handleClose }) {
  const [validated, setValidated] = useState(false);

  const schema: ZodType<UserData> = z
    .object({
      name: z.string().min(3).max(30),
      username: z.string().min(3).max(15),
      password: z.string().min(8).max(15),
      confirmPassword: z.string().min(8).max(15),
      email: z.string().email(),
      hours: z.number().min(20).max(40),
      userStatus: z.string(),
      role: z.string(),
    })
    .refine((data) => data.password === data.confirmPassword, {
      message: "Passwords do not match",
      path: ["confirmPassword"],
    });
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<UserData>({
    resolver: zodResolver(schema),
  });

  const registerUser = async (data: UserData) => {
    try {
      setValidated(true);
      alert("Team member created successfully");
      handleClose();
      await createTeamMember(data);
    } catch (e) {
      alert("Failed to create team member. Please try again.");
    }
  };

  return (
    <Form validated={validated} onSubmit={handleSubmit(registerUser)}>
      <Form.Group className="mb-3">
        <Form.Label>Name</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter name"
          {...register("name")}
          required
          isInvalid={!!errors.name}
        />
        <Form.Control.Feedback type="invalid">
          {errors.name?.message}
        </Form.Control.Feedback>{" "}
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Username</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter username"
          {...register("username")}
          required
          isInvalid={!!errors.username}
        />
        <Form.Control.Feedback type="invalid">
          {errors.username?.message}
        </Form.Control.Feedback>{" "}
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Password</Form.Label>
        <Form.Control
          type="password"
          placeholder="Enter password"
          {...register("password")}
          required
          isInvalid={!!errors.password}
        />
        <Form.Control.Feedback type="invalid">
          {errors.password?.message}
        </Form.Control.Feedback>{" "}
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Confirm Password</Form.Label>
        <Form.Control
          type="password"
          placeholder="Confirm password"
          {...register("confirmPassword")}
          required
          isInvalid={!!errors.confirmPassword}
        />
        <Form.Control.Feedback type="invalid">
          {errors.confirmPassword?.message}
        </Form.Control.Feedback>{" "}
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Email</Form.Label>
        <Form.Control
          type="email"
          placeholder="Enter email"
          {...register("email")}
          required
          isInvalid={!!errors.email}
        />
        <Form.Control.Feedback type="invalid">
          {errors.email?.message}
        </Form.Control.Feedback>{" "}
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Hours per week</Form.Label>
        <Form.Control
          type="number"
          placeholder="Enter hours per week"
          {...register("hours", { valueAsNumber: true })}
          required
          isInvalid={!!errors.hours}
        />
        <Form.Control.Feedback type="invalid">
          {errors.hours?.message}
        </Form.Control.Feedback>{" "}
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Status</Form.Label>
        <Form.Select {...register("userStatus")}>
          <option> Select user status status </option>
          <option value="ACTIVE">ACTIVE</option>
          <option value="INACTIVE">INACTIVE</option>
        </Form.Select>
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Role</Form.Label>
        <Form.Select {...register("role")}>
          <option>Select role</option>
          <option value="ADMIN">ADMIN</option>
          <option value="WORKER">WORKER</option>
        </Form.Select>
      </Form.Group>

      <Button variant="secondary" type="submit">
        Submit
      </Button>
    </Form>
  );
}
