"use client";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
} from "@/components/ui/card";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { TimePicker } from "@/components/ui/time-picker";
import { getAllExecutionPeriods } from "@/invokes/masterInvokesCall";
import {
  getDefaultFormattedDateTime,
  getMomentDateFromJSDate,
} from "@/lib/date-service";
import { zodResolver } from "@hookform/resolvers/zod";
import { open } from "@tauri-apps/api/dialog";
import { Folders, RotateCcwIcon } from "lucide-react";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { ExecutionPeriod, SelectedFolderDTO } from "../../@types";
import { useSelectedFolderAddEdit } from "../add-edit/_contexts/SelectedFolderAddEditProvider";

const formSchema = z.object({
  selected_folder: z.string().min(1, {
    message: "Required",
  }),
  execution_period_id: z.string().min(1, {
    message: "Required",
  }),
  execution_time: z.date(),
});

export default function SelectedFolderAddEdit() {
  const { state, dispatch } = useSelectedFolderAddEdit();
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: new SelectedFolderDTO(),
  });

  function onSubmit(values: z.infer<typeof formSchema>) {
    const chooseFolderDTO = new SelectedFolderDTO(values);
    chooseFolderDTO.execution_time = getDefaultFormattedDateTime(
      getMomentDateFromJSDate(values.execution_time),
    );

    //sendDataToRust(chooseFolderDTO);
  }

  const handleFolderSelect = async (field: any) => {
    try {
      const selected = await open({
        directory: true,
        multiple: false,
      });
      if (selected) {
        field.onChange(selected);
      }
    } catch (error) {
      console.error("Error selecting folder:", error);
    }
  };

  useEffect(() => {
    dispatch(getAllExecutionPeriods());
  }, [dispatch]);

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
        <Card className="w-full max-w-lg">
          <CardHeader></CardHeader>
          <CardContent className="grid gap-4">
            <FormField
              control={form.control}
              name="selected_folder"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Selected Folder *</FormLabel>
                  <FormControl>
                    <div className="grid grid-cols-12 gap-1">
                      <Input
                        contentEditable={false}
                        readOnly={field.value == ""}
                        className="col-span-10"
                        {...field}
                      />
                      <Button
                        type="button"
                        className="p-0 col-span-1"
                        onClick={() => handleFolderSelect(field)}
                      >
                        <Folders className="w-4" />
                      </Button>
                      <Button
                        type="button"
                        className="p-0 col-span-1"
                        onClick={() => field.onChange("")}
                      >
                        <RotateCcwIcon className="w-4" />
                      </Button>
                    </div>
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="execution_period_id"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Execution Period *</FormLabel>
                  <FormControl>
                    <Select onValueChange={field.onChange} value={field.value}>
                      <SelectTrigger>
                        <SelectValue />
                      </SelectTrigger>
                      <SelectContent>
                        {state.all_periods.length > 0 &&
                          state.all_periods.map((executionPeriod: ExecutionPeriod) => (
                            <SelectItem
                              key={executionPeriod.name}
                              value={`${executionPeriod.id}`}
                            >
                              {executionPeriod.description}
                            </SelectItem>
                          ))}
                        {}
                      </SelectContent>
                    </Select>
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="execution_time"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Execution Time *</FormLabel>
                  <FormControl>
                    <TimePicker setDate={field.onChange} date={field.value} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
          </CardContent>
          <CardFooter>
            <Button type="submit" className="w-full">
              Submit
            </Button>
          </CardFooter>
        </Card>
      </form>
    </Form>
  );
}
